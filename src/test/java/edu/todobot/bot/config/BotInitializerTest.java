package edu.todobot.bot.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class BotInitializerTest {
    @Mock
    private BotInitializer botInitializer = Mockito.mock(BotInitializer.class);

    @Test
    void createTelegramBotApiTest() {
        botInitializer.createTelegramBotApi();
        Mockito.verify(botInitializer).createTelegramBotApi();
    }
}