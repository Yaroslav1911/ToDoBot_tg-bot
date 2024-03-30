package edu.todobot.database.dto;

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
) {}
