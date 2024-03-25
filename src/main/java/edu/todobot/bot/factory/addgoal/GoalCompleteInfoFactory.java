package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.bot.service.actions.addgoal.GoalCompleteInfo;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.bot.factory.interfaces.ActionsFactory;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoalCompleteInfoFactory implements ActionsFactory {
    private final GoalsService goalsService;
    private final UsersService usersService;
    @Override
    public Actions run() {
        return new GoalCompleteInfo(goalsService, usersService);
    }

    @Override
    public List<String> getCommand() {
        return BotConfig.getCommand(CommandsData.NO_NEED_REMINDER.getCommand());
    }
}
