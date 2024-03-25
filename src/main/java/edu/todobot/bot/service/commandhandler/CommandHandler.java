package edu.todobot.bot.service.commandhandler;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class CommandHandler {
    private CommandHandler next;

    public CommandHandler setNext(CommandHandler next) {
        this.next = next;
        return next;
    }

    protected void checkNext(Update update) {
        if (next != null) {
            next.check(update);
        }
    }

    public abstract void check(Update update);
}
