package edu.todobot.bot.service.actions.interfaces;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface RequestActions {

    BotApiMethod handle(Update update);

    BotApiMethod callback(Update update);

}
