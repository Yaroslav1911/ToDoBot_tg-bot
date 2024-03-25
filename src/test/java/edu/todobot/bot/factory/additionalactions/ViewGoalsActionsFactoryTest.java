package edu.todobot.bot.factory.additionalactions;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewGoalsActionsFactoryTest {
    private final ViewGoalsActionsFactory factory = Mockito.mock(ViewGoalsActionsFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.VIEW_GOALS.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("View goals", "Подивитися задачі");

        assertEquals(expected, actual);
    }

}