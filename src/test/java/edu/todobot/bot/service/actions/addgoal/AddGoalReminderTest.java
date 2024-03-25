package edu.todobot.bot.service.actions.addgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class AddGoalReminderTest {

    @Mock
    private AddGoalReminder addGoalReminder = Mockito.mock(AddGoalReminder.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        addGoalReminder.handle(update);
        Mockito.verify(addGoalReminder).handle(update);
    }

    @Test
    void callbackTest() {
        addGoalReminder.callback(update);
        Mockito.verify(addGoalReminder).callback(update);
    }
}