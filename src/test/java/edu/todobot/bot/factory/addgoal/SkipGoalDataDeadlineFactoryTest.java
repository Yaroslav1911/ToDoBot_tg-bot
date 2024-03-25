package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkipGoalDataDeadlineFactoryTest {
    private final SkipGoalDataDeadlineFactory factory = Mockito.mock(SkipGoalDataDeadlineFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.NO_NEED_DEADLINE.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Don't need a deadline", "Крайній термін не потрібен");

        assertEquals(expected, actual);
    }

}