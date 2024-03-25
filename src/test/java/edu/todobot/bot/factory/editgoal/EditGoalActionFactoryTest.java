package edu.todobot.bot.factory.editgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EditGoalActionFactoryTest {
    private final EditGoalActionFactory factory = Mockito.mock(EditGoalActionFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.EDIT_GOALS.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Edit goals", "Редагувати задачі");

        assertEquals(expected, actual);
    }

}