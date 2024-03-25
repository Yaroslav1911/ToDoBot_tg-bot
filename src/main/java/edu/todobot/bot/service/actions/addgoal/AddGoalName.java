package edu.todobot.bot.service.actions.addgoal;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.service.actions.interfaces.RequestActions;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class AddGoalName implements RequestActions, Configurations {

    private final GoalsService goalsService;
    private final UsersService usersService;

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();
        var text = getBundle("enterName");
        usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_NAME), chatId);
        SendMessage sendMessage = ActionsUtils.addNextStepWithOneButton(chatId, getBundle("AGN_cancel"));
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_NAME))) {
            return new SendMessage(chatId.toString(), getBundle("AGN_canceled"));
        }

            String checkGoalName = msg.getText().replaceAll("\\W", "_");

        if (!goalsService.isGoalExistsByGoalName(checkGoalName)) {
            String goalName = msg.getText().replaceAll("\\W", "_");
            ActionsUtils.goalSet.put("goalName", goalName);
            usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_DESCRIPTION), chatId);
            SendMessage sendMessage = ActionsUtils.addNextStepWithThreeButtons(chatId,
                    getBundle("AGN_addDescription"),
                    getBundle("AGN_noNeedDescription"),
                    getBundle("AGN_cancel"));
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("AGN_added"));
            return sendMessage;

        } else {
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            return new SendMessage(chatId.toString(), getBundle("nameExists"));
        }
    }
}
