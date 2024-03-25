package edu.todobot.database.dto;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.database.entities.UsersEntity;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record GoalsDto(
        UUID id,
        String goalName,
        String goalDescription,
        LocalDateTime goalDeadline,
        LocalDateTime goalReminder,
        UsersEntity user
) {

    @Override
    public String toString() {
        return String.format(BotConfig.bundle.getString("GE_toString"),
                goalName, goalDescription, goalDeadline, replaceT(goalReminder));
    }

    private String replaceT (LocalDateTime goalReminder) {
        if (goalReminder == null) {
            return "null";
        }
        return goalReminder.toString().replace("T", " ");
    }
}
