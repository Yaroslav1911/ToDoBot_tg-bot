package edu.todobot.bot.factory.editgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeGoalDescriptionFactoryTest {
    private final ChangeGoalDescriptionFactory factory = Mockito.mock(ChangeGoalDescriptionFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.CHANGE_DESCRIPTION.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Change description", "Змінити опис");

        assertEquals(expected, actual);
    }

}