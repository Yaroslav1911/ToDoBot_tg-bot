package edu.todobot.bot.service.actions.editgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class EditGoalActionTest {

    @Mock
    private EditGoalAction editGoalAction = Mockito.mock(EditGoalAction.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        editGoalAction.handle(update);
        Mockito.verify(editGoalAction).handle(update);
    }

    @Test
    void callbackTest() {
        editGoalAction.callback(update);
        Mockito.verify(editGoalAction).callback(update);
    }
}