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

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EditGoalAction implements RequestActions, Configurations {
    private final GoalsService goalsService;
    private final UsersService usersService;

    @Override
    @SuppressWarnings("unchecked")
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if(goalsService.findAllGoalsByUserId(chatId).isEmpty()){
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("EG_noGoals"));
            return sendMessage;
        } else {
            usersService.setUsersChatState(String.valueOf(ChatState.CHOOSING_GOAL_TO_EDIT), chatId);
            var goalsNames = goalsService.getAllGoalsNames(chatId);
            Collection collect = new ArrayList<>(goalsNames);
            String out = collect.stream().map(s -> "/" + s)
                    .collect(Collectors.joining("\n")).toString();

            return new SendMessage(chatId.toString(), getBundle("EG_select") + out);
        }
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg= update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.CHOOSING_GOAL_TO_EDIT))) {
            return new SendMessage(chatId.toString(), "");
        }

        usersService.setUsersChatState(String.valueOf(ChatState.GOAL_EDITING), chatId);
        String goalName = msg.getText().replaceAll("/", "");
        var goal = goalsService.getGoalByGoalName(goalName);
        ActionsUtils.goalID = goal.id();
        String out = ActionsUtils.getStringTextOfGoal(goal);
        SendMessage sendMessage = ActionsUtils.addEditGoalDataButtons(chatId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(out);

        return sendMessage;

    }
}
