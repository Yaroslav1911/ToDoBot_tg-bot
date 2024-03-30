package edu.todobot.database.mappers;

import edu.todobot.database.dto.GoalsDto;
import edu.todobot.database.entities.GoalsEntity;
import org.springframework.stereotype.Component;

@Component
public class GoalsMapper {
    public GoalsDto toDto(GoalsEntity goalsEntity) {
        return GoalsDto.builder()
                .id(goalsEntity.getId())
                .goalName(goalsEntity.getGoalName())
                .goalDescription(goalsEntity.getGoalDescription())
                .goalDeadline(goalsEntity.getGoalDeadline())
                .goalReminder(goalsEntity.getGoalReminder())
                .user(goalsEntity.getUser())
                .build();
    }
}
