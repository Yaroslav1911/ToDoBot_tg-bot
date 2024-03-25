package edu.todobot.bot.service.actions.additionalactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class ViewGoalsActionTest {

    @Mock
    private ViewGoalsAction viewGoalsAction = Mockito.mock(ViewGoalsAction.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        viewGoalsAction.handle(update);
        Mockito.verify(viewGoalsAction).handle(update);
    }
}