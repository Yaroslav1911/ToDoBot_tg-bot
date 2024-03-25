package edu.todobot.bot.service.actions.editgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class ChangeGoalReminderTest {

    @Mock
    private ChangeGoalReminder changeGoalReminder = Mockito.mock(ChangeGoalReminder.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        changeGoalReminder.handle(update);
        Mockito.verify(changeGoalReminder).handle(update);
    }

    @Test
    void callbackTest() {
        changeGoalReminder.callback(update);
        Mockito.verify(changeGoalReminder).callback(update);
    }
}