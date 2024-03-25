package edu.todobot.bot.config;

import edu.todobot.bot.service.ToDoBotEngine;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/** @noinspection UnusedReturnValue*/
@Component
public class BotInitializer {
    private final ToDoBotEngine bot;
    private final TelegramBotsApi telegramBotsApi;

    public BotInitializer(ToDoBotEngine bot) throws TelegramApiException {
        this.bot = bot;
        this.telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    }

    @EventListener({ContextRefreshedEvent.class})
    public TelegramBotsApi createTelegramBotApi() {
        try {
            telegramBotsApi.registerBot(bot);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }
}