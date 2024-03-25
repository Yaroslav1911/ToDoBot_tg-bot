package edu.todobot.bot.service.actions.additionalactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class StartActionTest {

    @Mock
    private StartAction startAction = Mockito.mock(StartAction.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handle() {
        startAction.handle(update);
        Mockito.verify(startAction).handle(update);
    }
}