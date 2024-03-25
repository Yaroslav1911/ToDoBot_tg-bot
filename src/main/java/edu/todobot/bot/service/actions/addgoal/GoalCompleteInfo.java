package edu.todobot.bot.service.actions.addgoal;

import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.bot.service.actions.interfaces.Actions;
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
public class GoalCompleteInfo implements Actions, Configurations {

    private final GoalsService goalsService;
    private final UsersService usersService;

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_REMINDER))) {
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            ActionsUtils.goalSet.put("reminder", null);

            putInGoalsTable(chatId);

            SendMessage sendMessage = ActionsUtils.addMainButtons(msg.getChatId());
            sendMessage.setChatId(chatId);
            String out = ActionsUtils.getStringFromMapOfGoal();
            sendMessage.setText(out);
            ActionsUtils.goalSet.clear();
            return sendMessage;
        } else {
            return new SendMessage(chatId.toString(), getBundle("GCI_someError"));
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
