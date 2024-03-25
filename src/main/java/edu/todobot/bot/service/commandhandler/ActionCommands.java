package edu.todobot.bot.service.commandhandler;

import edu.todobot.bot.data.ActionsData;
import edu.todobot.bot.factory.interfaces.ActionsFactory;
import edu.todobot.bot.service.ToDoBotEngine;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class ActionCommands extends CommandHandler{
    private final ActionsData actionsData;
    private final ToDoBotEngine botEngine;
    @Override
    public void check(Update update) {
        var messageText = update.getMessage().getText();
        ActionsFactory actionsFactory =
                actionsData.getActionsFactory(messageText);

        if (actionsFactory != null) {
            var msg = actionsFactory.run().handle(update);
            botEngine.send(msg);
        }

        checkNext(update);
    }
}
