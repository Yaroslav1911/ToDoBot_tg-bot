package edu.todobot.bot.service.actions.addgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class AddGoalNameTest {

    @Mock
    private AddGoalName addGoalName = Mockito.mock(AddGoalName.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        addGoalName.handle(update);
        Mockito.verify(addGoalName).handle(update);
    }

    @Test
    void callbackTest() {
        addGoalName.callback(update);
        Mockito.verify(addGoalName).callback(update);
    }
}