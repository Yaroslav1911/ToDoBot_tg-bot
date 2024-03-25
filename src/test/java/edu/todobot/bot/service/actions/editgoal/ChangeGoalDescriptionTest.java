package edu.todobot.bot.service.actions.editgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class ChangeGoalDescriptionTest {

    @Mock
    private ChangeGoalDescription changeGoalDescription = Mockito.mock(ChangeGoalDescription.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        changeGoalDescription.handle(update);
        Mockito.verify(changeGoalDescription).handle(update);
    }

    @Test
    void callbackTest() {
        changeGoalDescription.callback(update);
        Mockito.verify(changeGoalDescription).callback(update);
    }
}