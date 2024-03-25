package edu.todobot.bot.factory.interfaces;

import edu.todobot.bot.service.actions.interfaces.RequestActions;

import java.util.List;

public interface RequestActionsFactory {
    RequestActions run();

    List<String> getCommand();
}
