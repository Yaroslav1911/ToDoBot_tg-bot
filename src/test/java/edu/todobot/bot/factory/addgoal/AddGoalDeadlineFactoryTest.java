package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddGoalDeadlineFactoryTest {
    private final AddGoalDeadlineFactory factory = Mockito.mock(AddGoalDeadlineFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.ADD_DEADLINE.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Add deadline", "Додати крайній термін");

        assertEquals(expected, actual);
    }
}