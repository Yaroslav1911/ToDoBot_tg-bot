package edu.todobot.bot.factory.additionalactions;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.bot.service.actions.additionalactions.ViewGoalsAction;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.bot.factory.interfaces.ActionsFactory;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ViewGoalsActionsFactory implements ActionsFactory {
    private final GoalsService goalsService;
    private final UsersService usersService;
    @Override
    public Actions run() {
        return new ViewGoalsAction(goalsService, usersService);
    }

    @Override
    public List<String> getCommand() {
        return BotConfig.getCommand(CommandsData.VIEW_GOALS.getCommand());
    }
}