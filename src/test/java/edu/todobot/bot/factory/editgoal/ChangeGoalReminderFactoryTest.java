package edu.todobot.bot.factory.editgoal;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeGoalReminderFactoryTest {
    private final ChangeGoalReminderFactory factory = Mockito.mock(ChangeGoalReminderFactory.class);
    @Test
    void run() {
        factory.run();
        Mockito.verify(factory).run();
    }

    @Test
    void getCommand() {
        Mockito.when(factory.getCommand()).thenReturn(BotConfig.getCommand(CommandsData.CHANGE_REMINDER.getCommand()));

        var actual = factory.getCommand();
        var expected = List.of("Change reminder", "Змінити нагадування");

        assertEquals(expected, actual);
    }

}