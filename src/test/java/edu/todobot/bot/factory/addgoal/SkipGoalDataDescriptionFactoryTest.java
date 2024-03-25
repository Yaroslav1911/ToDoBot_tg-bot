package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkipGoalDataDescriptionFactoryTest {
    private final SkipGoalDataDescriptionFactory factory = Mockito.mock(SkipGoalDataDescriptionFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.NO_NEED_DESCRIPTION.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Don't need a description", "Опис не потрібен");

        assertEquals(expected, actual);
    }

}