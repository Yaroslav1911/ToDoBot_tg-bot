package edu.todobot.bot.service.actions.editgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class ChangeGoalNameTest {

    @Mock
    private ChangeGoalName changeGoalName = Mockito.mock(ChangeGoalName.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        changeGoalName.handle(update);
        Mockito.verify(changeGoalName).handle(update);
    }

    @Test
    void callbackTest() {
        changeGoalName.callback(update);
        Mockito.verify(changeGoalName).callback(update);
    }
}