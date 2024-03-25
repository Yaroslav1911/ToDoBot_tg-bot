package edu.todobot.bot.factory.addgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddGoalDescriptionFactoryTest {
    private final AddGoalDescriptionFactory factory = Mockito.mock(AddGoalDescriptionFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.ADD_DESCRIPTION.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Add description", "Додати опис(любою мовою)");

        assertEquals(expected, actual);
    }

}