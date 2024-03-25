package edu.todobot.bot.service.actions.editgoal;

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
public class ChangeGoalDeadline implements RequestActions, Configurations {
    private final GoalsService goalsService;
    private final UsersService usersService;

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.GOAL_EDITING))){
            usersService.setUsersChatState(String.valueOf(ChatState.CHANGING_DEADLINE), chatId);
            return new SendMessage(chatId.toString(), getBundle("CGDl_newDeadline"));
        } else {
            return new SendMessage(chatId.toString(), getBundle("CGDl_someError"));
        }
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.CHANGING_DEADLINE))) {
            return new SendMessage(chatId.toString(), "");
        }

        try {
            var newDeadline = ActionsUtils.formatterFromStringToLocalDateTime(msg.getText());

            usersService.setUsersChatState(String.valueOf(ChatState.GOAL_EDITING), chatId);
            goalsService.updateGoalDeadline(newDeadline, ActionsUtils.goalID);
            var goal = goalsService.getGoalByGoalId(ActionsUtils.goalID);
            String out = ActionsUtils.getStringTextOfGoal(goal);

            return new SendMessage(chatId.toString(), getBundle("CGDl_deadlineChanged") + out);
        } catch (Exception e) {
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("CGDl_unsupportedFormat"));
            return sendMessage;
        }
    }
}
