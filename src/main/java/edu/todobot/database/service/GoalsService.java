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
        return goalsRepository.findAll().stream()
                .map(goalsMapper::toDto)
                .filter(g -> g.user().getId().equals(chatId))
                .toList();
    }
    public boolean isGoalExistsByGoalName(String goalName){
        return goalsRepository.findAll().stream()
                .map(goalsMapper::toDto)
                .anyMatch(g -> g.goalName().equals(goalName));
    }
    public long getUserIdByReminder(LocalDateTime ldt){
        return goalsRepository.findAll().stream()
                .map(goalsMapper::toDto)
                .filter(g -> g.goalReminder() != null)
                .filter(g -> g.goalReminder().equals(ldt))
                .toList().get(0).user().getId();
    }
    public GoalsDto getGoalByGoalName(String goalName){
        return goalsRepository.findAll().stream()
                .map(goalsMapper::toDto)
                .filter(g -> g.goalName().equals(goalName))
                .toList().get(0);
    }
    public List<String> getAllGoalsNames(Long chatId) {
        return goalsRepository.findAll().stream()
                .map(goalsMapper::toDto)
                .filter(g -> g.user().getId().equals(chatId))
                .map(GoalsDto::goalName).toList();
    }
    public GoalsDto getGoalByGoalId(UUID goalId){
        return goalsRepository.findById(goalId).stream()
                .map(goalsMapper::toDto).toList().get(0);
    }
    @Transactional
    public void deleteGoalById(UUID goalId){
        goalsRepository.deleteById(goalId);
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
