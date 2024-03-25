package edu.todobot.bot.factory.editgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeGoalNameFactoryTest {
    private final ChangeGoalNameFactory factory = Mockito.mock(ChangeGoalNameFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.CHANGE_NAME.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Change name", "Змінити назву");

        assertEquals(expected, actual);
    }

}