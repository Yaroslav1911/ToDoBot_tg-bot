package edu.todobot.database.repository;

import edu.todobot.database.entities.GoalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface GoalsRepository extends JpaRepository<GoalsEntity, UUID> {

    List<GoalsEntity> findAllByUserId(long chatId);
    boolean existsByGoalName(String goalName);
    GoalsEntity getByGoalReminder(LocalDateTime localDateTime);
    GoalsEntity findByGoalName(String goalName);
    List<GoalsEntity> findAllGoalNamesByUserId(long chatId);
}
