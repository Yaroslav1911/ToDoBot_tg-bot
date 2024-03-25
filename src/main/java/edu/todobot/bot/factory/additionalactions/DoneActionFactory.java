package edu.todobot.bot.factory.additionalactions;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.bot.service.actions.additionalactions.DoneAction;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.bot.factory.interfaces.ActionsFactory;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoneActionFactory implements ActionsFactory {
    private final UsersService usersService;
    @Override
    public Actions run() {
        return new DoneAction(usersService);
    }

    @Override
    public List<String> getCommand() {
        return BotConfig.getCommand(CommandsData.DONE.getCommand());
    }
}
