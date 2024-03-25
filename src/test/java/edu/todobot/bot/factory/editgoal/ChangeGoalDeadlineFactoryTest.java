package edu.todobot.bot.factory.editgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeGoalDeadlineFactoryTest {
    private final ChangeGoalDeadlineFactory factory = Mockito.mock(ChangeGoalDeadlineFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.CHANGE_DEADLINE.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Change deadline", "Змінити крайній термін");

        assertEquals(expected, actual);
    }

}