package edu.todobot.bot.service.actions.addgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class AddGoalDescriptionTest {

    @Mock
    private AddGoalDescription addGoalDescription = Mockito.mock(AddGoalDescription.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handleTest() {
        addGoalDescription.handle(update);
        Mockito.verify(addGoalDescription).handle(update);
    }

    @Test
    void callbackTest() {
        addGoalDescription.callback(update);
        Mockito.verify(addGoalDescription).callback(update);
    }
}