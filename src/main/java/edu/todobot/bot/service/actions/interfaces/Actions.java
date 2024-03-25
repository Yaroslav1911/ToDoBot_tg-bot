package edu.todobot.bot.service.actions.interfaces;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Actions {
    BotApiMethod handle(Update update);
}
