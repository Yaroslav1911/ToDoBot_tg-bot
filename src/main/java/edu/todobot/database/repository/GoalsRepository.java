package edu.todobot.database.repository;

import edu.todobot.database.entities.GoalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GoalsRepository extends JpaRepository<GoalsEntity, UUID> {}
