package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.bot.factory.interfaces.RequestActionsFactory;
import edu.todobot.bot.service.actions.addgoal.AddGoalReminder;
import edu.todobot.bot.service.actions.interfaces.RequestActions;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddGoalReminderFactory implements RequestActionsFactory {
    private final GoalsService goalsService;
    private final UsersService usersService;
    @Override
    public RequestActions run() {
        return new AddGoalReminder(goalsService, usersService);
    }

    @Override
    public List<String> getCommand() {
        return BotConfig.getCommand(CommandsData.ADD_REMINDER.getCommand());
    }
}
