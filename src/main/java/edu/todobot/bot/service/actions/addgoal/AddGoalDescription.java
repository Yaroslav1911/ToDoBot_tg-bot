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
public class AddGoalDescription implements RequestActions, Configurations {
    private final UsersService usersService;
    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DESCRIPTION))) {
            SendMessage sendMessage = ActionsUtils.addNextStepWithOneButton(chatId, getBundle("AGD_cancel"));
            sendMessage.setChatId(chatId);
            var text = getBundle("AGD_writeDescription");
            sendMessage.setText(text);
            return sendMessage;
        } else {
            return new SendMessage(chatId.toString(), getBundle("AGD_someError"));
        }
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DESCRIPTION))) {
            return new SendMessage(chatId.toString(), "");
        }

        ActionsUtils.goalSet.put("description", msg.getText());
        SendMessage sendMessage = ActionsUtils.addNextStepWithThreeButtons(chatId,
                getBundle("AGD_addDeadline"),
                getBundle("AGD_noNeedDeadline"),
                getBundle("AGD_cancel"));
        sendMessage.setChatId(chatId);
        sendMessage.setText(getBundle("AGD_descriptionAdded"));
        usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_DEADLINE), chatId);
        return sendMessage;
    }

}
