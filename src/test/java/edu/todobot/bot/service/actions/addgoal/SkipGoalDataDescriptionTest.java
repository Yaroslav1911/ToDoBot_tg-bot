package edu.todobot.bot.service.actions.addgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class SkipGoalDataDescriptionTest {

    @Mock
    private SkipGoalDataDescription skipGoalDataDescription = Mockito.mock(SkipGoalDataDescription.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handle() {
        skipGoalDataDescription.handle(update);
        Mockito.verify(skipGoalDataDescription).handle(update);
    }
}