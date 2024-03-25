package edu.todobot.bot.service.actions.addgoal;

import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.bot.service.actions.interfaces.RequestActions;
import edu.todobot.database.entities.GoalsEntity;
import edu.todobot.database.entities.UsersEntity;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.Objects;

@Transactional
@Component
@RequiredArgsConstructor
public class AddGoalReminder implements RequestActions, Configurations {

    private final GoalsService goalsService;
    private final UsersService usersService;

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if(usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DEADLINE))){
            usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_REMINDER), chatId);
        }

        if(usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_REMINDER))) {
            SendMessage sendMessage = ActionsUtils.addNextStepWithOneButton(chatId, getBundle("AGR_cancel"));
            sendMessage.setText(chatId.toString());
            sendMessage.setText(getBundle("AGR_enterReminder"));
            return sendMessage;
        } else {
            return new SendMessage(chatId.toString(), getBundle("AGR_someError"));
        }
    }

    @Override
    public BotApiMethod callback(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_REMINDER))) {
            return new SendMessage(chatId.toString(), "");
        }

        try {
            ActionsUtils.formatterFromStringToLocalDateTime(msg.getText());

            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            ActionsUtils.goalSet.put("reminder", msg.getText());

            putInGoalsTable(chatId);

            SendMessage sendMessage = ActionsUtils.addMainButtons(msg.getChatId());
            sendMessage.setChatId(chatId);
            String out = ActionsUtils.getStringFromMapOfGoal();
            sendMessage.setText(out);
            ActionsUtils.goalSet.clear();
            return sendMessage;
        } catch (Exception e) {
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("AGR_notSupportedFormat"));
            return sendMessage;
        }
    }

    private void putInGoalsTable(Long chatId) {
        String goalName = ActionsUtils.goalSet.get("goalName");
        String goalDescription = ActionsUtils.goalSet.get("description");
        LocalDateTime goalDeadline;
        LocalDateTime goalReminder;

        if (Objects.nonNull(ActionsUtils.goalSet.get("deadline"))) {
            goalDeadline = ActionsUtils.formatterFromStringToLocalDateTime(ActionsUtils.goalSet.get("deadline"));
        } else goalDeadline = null;

        if (Objects.nonNull(ActionsUtils.goalSet.get("reminder"))) {
            goalReminder = ActionsUtils.formatterFromStringToLocalDateTime(ActionsUtils.goalSet.get("reminder"));
        } else goalReminder = null;

        UsersEntity user = usersService.findById(chatId).stream().findFirst().orElse(null);
        GoalsEntity goal = new GoalsEntity();
        goal.setGoalName(goalName);
        goal.setGoalDescription(goalDescription);
        goal.setGoalDeadline(goalDeadline);
        goal.setGoalReminder(goalReminder);
        goal.setUser(user);

        goalsService.saveAndFlush(goal);
    }
}
