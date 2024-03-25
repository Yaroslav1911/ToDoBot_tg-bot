package edu.todobot.database.dto;

import edu.todobot.database.entities.GoalsEntity;
import lombok.Builder;

import java.util.Set;

@Builder
public record UsersDto(String userName, Long id, String firstName, String lastName,
                       String lang, String chatState, Set<GoalsEntity> goals) {}
