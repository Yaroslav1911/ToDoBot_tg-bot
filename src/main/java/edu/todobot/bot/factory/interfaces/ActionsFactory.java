package edu.todobot.bot.factory.interfaces;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.service.actions.interfaces.Actions;

import java.util.List;

public interface ActionsFactory extends Configurations {
    Actions run();
    List<String> getCommand();
}
