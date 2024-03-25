package edu.todobot.bot.factory.additionalactions;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoneActionFactoryTest {
    private final DoneActionFactory factory = Mockito.mock(DoneActionFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.DONE.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Done", "Готово");

        assertEquals(expected, actual);
    }

}