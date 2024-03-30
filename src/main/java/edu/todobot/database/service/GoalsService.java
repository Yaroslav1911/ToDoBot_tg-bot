package edu.todobot.database.service;

import edu.todobot.database.dto.GoalsDto;
import edu.todobot.database.entities.GoalsEntity;
import edu.todobot.database.mappers.GoalsMapper;
import edu.todobot.database.repository.GoalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalsService {
    private final GoalsRepository goalsRepository;
    private final GoalsMapper goalsMapper;
    public List<GoalsDto> findAllGoals(){
        return goalsRepository.findAll().stream()
                .map(goalsMapper::toDto).toList();
    }
    public List<GoalsDto> findAllGoalsByUserId(long chatId) {
        return goalsRepository.findAllByUserId(chatId).stream()
                .map(goalsMapper::toDto).toList();
    }
    public boolean isGoalExistsByGoalName(String goalName){
        return goalsRepository.existsByGoalName(goalName);
    }
    public long getUserIdByReminder(LocalDateTime ldt){
        return goalsMapper.toDto(goalsRepository.getByGoalReminder(ldt)).user().getId();
    }
    public GoalsDto findByGoalName(String goalName){
        return goalsMapper.toDto(goalsRepository.findByGoalName(goalName));
    }
    public List<String> getAllGoalsNames(long chatId) {
        return goalsRepository.findAllGoalNamesByUserId(chatId).stream()
                .map(GoalsEntity::getGoalName)
                .toList();
    }
    public GoalsDto findById(UUID goalId){
        return goalsRepository.findById(goalId).map(goalsMapper::toDto).orElse(null);
    }
    @Transactional
    public void deleteGoalById(UUID goalId){
        goalsRepository.deleteById(goalId);
        goalsRepository.flush();
    }
    @Transactional
    public void saveAndFlush(GoalsEntity goal){
        goalsRepository.saveAndFlush(goal);
    }
    @Transactional
    public void updateGoalReminder(LocalDateTime ldt, UUID goalId) {
        goalsRepository.getReferenceById(goalId).setGoalReminder(ldt);
    }
    @Transactional
    public void updateGoalDeadline(LocalDateTime ldt, UUID goalId) {
        goalsRepository.getReferenceById(goalId).setGoalDeadline(ldt);
    }
    @Transactional
    public void updateGoalDescription(String newDescription, UUID goalId){
        goalsRepository.getReferenceById(goalId).setGoalDescription(newDescription);
    }
    @Transactional
    public void updateGoalName(String newName, UUID goalId){
        goalsRepository.getReferenceById(goalId).setGoalName(newName);
    }
}
