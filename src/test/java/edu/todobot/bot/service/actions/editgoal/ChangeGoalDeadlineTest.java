package edu.todobot.bot.service.actions.editgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class ChangeGoalDeadlineTest {

    @Mock
    private ChangeGoalDeadline changeGoalDeadline = Mockito.mock(ChangeGoalDeadline.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        changeGoalDeadline.handle(update);
        Mockito.verify(changeGoalDeadline).handle(update);
    }

    @Test
    void callbackTest() {
        changeGoalDeadline.callback(update);
        Mockito.verify(changeGoalDeadline).callback(update);
    }
}