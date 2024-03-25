package edu.todobot.bot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class ToDoBotEngineTest {

    @Mock
    private ToDoBotEngine bot = Mockito.mock(ToDoBotEngine.class);

    @Mock
    private TelegramLongPollingBot telegramBot = Mockito.mock(TelegramLongPollingBot.class);

    @Test
    void onUpdateReceivedTest() {
        Mockito.doCallRealMethod().when(bot).onUpdatesReceived(any());
        Update update1 = new Update();
        update1.setUpdateId(1);
        Update update2 = new Update();
        update1.setUpdateId(2);
        bot.onUpdatesReceived(asList(update1, update2));
        Mockito.verify(bot).onUpdateReceived(update1);
        Mockito.verify(bot).onUpdateReceived(update2);
    }

    @Test
    void sendTest() {
        bot.send(new SendMessage("1", "test"));
        Mockito.verify(bot).send(new SendMessage("1", "test"));
    }

    @Test
    void getBotNameTest() {
        Mockito.when(telegramBot.getBotUsername()).thenReturn("TestBotName");

        assertEquals("TestBotName", telegramBot.getBotUsername());
    }
}