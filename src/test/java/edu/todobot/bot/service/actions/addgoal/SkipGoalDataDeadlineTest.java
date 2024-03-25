package edu.todobot.bot.service.actions.addgoal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

class SkipGoalDataDeadlineTest {

    @Mock
    private SkipGoalDataDeadline skipGoalDataDeadline = Mockito.mock(SkipGoalDataDeadline.class);
    private Update update;

    @BeforeEach
    void setUp() {
        update = new Update();
        update.setUpdateId(1);
    }

    @Test
    void handle() {
        skipGoalDataDeadline.handle(update);
        Mockito.verify(skipGoalDataDeadline).handle(update);
    }
}