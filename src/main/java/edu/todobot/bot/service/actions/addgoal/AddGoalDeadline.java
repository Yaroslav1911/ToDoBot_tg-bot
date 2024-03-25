package edu.todobot.bot.service.actions.addgoal;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.service.actions.interfaces.RequestActions;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class AddGoalDeadline implements RequestActions, Configurations {
    private final UsersService usersService;
    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DESCRIPTION))) {
            usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_DEADLINE), chatId);
        }

        if(usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DEADLINE))) {
            SendMessage sendMessage = ActionsUtils.addNextStepWithOneButton(chatId, getBundle("AGDl_cancel"));
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("AGDl_enterDeadline"));
            return sendMessage;
        } else {
            return new SendMessage(chatId.toString(), getBundle("AGDl_someError"));
        }
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();


        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DEADLINE))) {
            return new SendMessage(chatId.toString(), "");
        }

        try {
            ActionsUtils.formatterFromStringToLocalDateTime(msg.getText());

            ActionsUtils.goalSet.put("deadline", msg.getText());
            usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_REMINDER), chatId);
            SendMessage sendMessage = ActionsUtils.addNextStepWithThreeButtons(chatId,
                    getBundle("AGDl_addReminder"),
                    getBundle("AGDl_noNeedReminder"),
                    getBundle("AGDl_cancel"));
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("AGDl_deadlineAdded"));
            return sendMessage;
        } catch (Exception e){
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("AGDl_notSupportedFormat"));
            return sendMessage;
        }
    }
}
