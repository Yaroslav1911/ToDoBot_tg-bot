package edu.todobot.bot.service.actions.addgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class AddGoalDeadlineTest {

    @Mock
    private AddGoalDeadline addGoalDeadline = Mockito.mock(AddGoalDeadline.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        addGoalDeadline.handle(update);
        Mockito.verify(addGoalDeadline).handle(update);
    }

    @Test
    void callbackTest() {
        addGoalDeadline.callback(update);
        Mockito.verify(addGoalDeadline).callback(update);
    }
}