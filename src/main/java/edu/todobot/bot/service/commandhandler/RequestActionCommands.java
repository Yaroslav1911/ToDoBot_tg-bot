package edu.todobot.bot.service.commandhandler;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ActionsData;
import edu.todobot.bot.service.ToDoBotEngine;
import edu.todobot.bot.factory.interfaces.RequestActionsFactory;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

@RequiredArgsConstructor
public class RequestActionCommands extends CommandHandler implements Configurations {

    private final ActionsData actionsData;
    private final ToDoBotEngine botEngine;
    @Override
    public void check(Update update) {
        var messageText = update.getMessage().getText();
        var chatId = update.getMessage().getChatId().toString();
        RequestActionsFactory requestActionsFactory = actionsData.getRequestActionsFactory(messageText);

        if (requestActionsFactory != null) {
            var msg = requestActionsFactory.run().handle(update);
            bindingBy().put(chatId, messageText);
            botEngine.send(msg);
        } else if (bindingBy().containsKey(chatId)) {
            RequestActionsFactory requestActionsFactory1 =
                    actionsData.getRequestActionsFactory(bindingBy().get(chatId));
            var msg = requestActionsFactory1.run().callback(update);
            bindingBy().remove(chatId);
            if (Objects.nonNull(msg)) {
                botEngine.send(msg);
            }
        }

        checkNext(update);
    }
}
