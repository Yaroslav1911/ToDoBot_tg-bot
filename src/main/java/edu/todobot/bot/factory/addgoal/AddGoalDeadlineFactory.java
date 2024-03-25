package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.bot.factory.interfaces.RequestActionsFactory;
import edu.todobot.bot.service.actions.addgoal.AddGoalDeadline;
import edu.todobot.bot.service.actions.interfaces.RequestActions;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddGoalDeadlineFactory implements RequestActionsFactory {
    private final UsersService usersService;
    @Override
    public RequestActions run() {
        return new AddGoalDeadline(usersService);
    }

    @Override
    public List<String> getCommand() {
        return BotConfig.getCommand(CommandsData.ADD_DEADLINE.getCommand());
    }
}
