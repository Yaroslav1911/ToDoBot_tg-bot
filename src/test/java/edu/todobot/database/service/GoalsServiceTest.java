package edu.todobot.database.service;

import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.data.LocaleData;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.dto.GoalsDto;
import edu.todobot.database.entities.GoalsEntity;
import edu.todobot.database.entities.UsersEntity;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class GoalsServiceTest {

    @Autowired
    private GoalsService goalsService;
    @Autowired
    private UsersService usersService;


    @Autowired
    private EntityManager entityManager;
    @Test
    void findAllGoalsTest() {
        Assertions.assertEquals(5, goalsService.findAllGoals().size());
    }

    @Test
    void findAllGoalsByUserIdTest() {
        Assertions.assertEquals(2, goalsService.findAllGoalsByUserId(55554L).size());
    }

    @Test
    void isGoalExistsByGoalNameTest() {
        assertThat(goalsService.isGoalExistsByGoalName("secondGoal")).isTrue();
    }

    @Test
    void getUserIdByReminderTest() {
        LocalDateTime ldt = ActionsUtils.formatterFromStringToLocalDateTime("24-03-26 18:31");
        long actualUsrId = goalsService.getUserIdByReminder(ldt);

        Assertions.assertEquals(55554L, actualUsrId);
    }

    @Test
    void getGoalByGoalNameTest() {
        GoalsDto actualGoal = goalsService.findByGoalName("firstGoal");
        Assertions.assertEquals("firstGoal", actualGoal.goalName());
    }

    @Test
    void getAllGoalsNamesTest() {
        List<String> actualList = goalsService.getAllGoalsNames(55554L);
        List<String> expectedList = List.of("firstGoal", "secondGoal");

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void findByIdTest() {
        UUID goalId = goalsService.findByGoalName("forFindById").id();

        GoalsDto goalById = goalsService.findById(goalId);
        Assertions.assertEquals("Testing finById method.//", goalById.goalDescription());
        Assertions.assertEquals("forFindById", goalById.goalName());
    }

    @Test
    void saveAndFlushTest() {
        GoalsEntity goal = getGoalsEntity();

        int count = goalsService.findAllGoals().size();
        Assertions.assertEquals(5, count);

        goalsService.saveAndFlush(goal);

        int count2 = goalsService.findAllGoals().size();
        Assertions.assertEquals(6, count2);
    }



    @Test
    void updateGoalReminderTest() {
        LocalDateTime ldt = ActionsUtils.formatterFromStringToLocalDateTime("24-03-26 18:31");
        GoalsDto goal1 = goalsService.findByGoalName("secondGoal");

        assertThat(goal1.goalReminder()).isNull();


        goalsService.updateGoalReminder(ldt, goal1.id());


        GoalsDto goal2 = goalsService.findByGoalName("secondGoal");
        Assertions.assertEquals(ldt, goal2.goalReminder());
    }

    @Test
    void updateGoalDeadlineTest() {
        LocalDateTime ldt = ActionsUtils.formatterFromStringToLocalDateTime("24-03-26 18:31");
        GoalsDto goal1 = goalsService.findByGoalName("thirdGoal");

        assertThat(goal1.goalDeadline()).isNull();


        goalsService.updateGoalDeadline(ldt, goal1.id());


        GoalsDto goal2 = goalsService.findByGoalName("thirdGoal");
        Assertions.assertEquals(ldt, goal2.goalDeadline());
    }

    @Test
    void updateGoalDescriptionTest() {
        String actualDescription = "Testing finById method.//";
        GoalsDto goal1 = goalsService.findByGoalName("fourthGoal");

        assertThat(goal1.goalDescription()).isEqualTo(actualDescription);

        String newDescription = "It's a new desc&&&";
        goalsService.updateGoalDescription(newDescription, goal1.id());


        GoalsDto goal2 = goalsService.findByGoalName("fourthGoal");
        Assertions.assertEquals(newDescription, goal2.goalDescription());
    }

    @Test
    void updateGoalNameTest() {
        GoalsDto goal1 = goalsService.findByGoalName("thirdGoal");
        UUID id = goal1.id();

        assertThat(goal1.goalName()).isEqualTo("thirdGoal");

        String newName = "newName";
        goalsService.updateGoalName(newName, id);


        GoalsDto goal2 = goalsService.findById(id);
        Assertions.assertEquals(newName, goal2.goalName());
    }

    @Test
    @Transactional
    void deleteGoalByIdTest() {
        Assertions.assertTrue(goalsService.isGoalExistsByGoalName("fourthGoal"));
        int size = goalsService.findAllGoalsByUserId(5555666L).size();

        UUID goalId = goalsService.findByGoalName("fourthGoal").id();

        goalsService.deleteGoalById(goalId);

        Assertions.assertEquals(size - 1, goalsService.findAllGoalsByUserId(5555666L).size());
    }

    @NotNull
    private GoalsEntity getGoalsEntity() {
        UsersEntity user = new UsersEntity();
        user.setId(884654L);
        user.setUserName("user");
        user.setLang(LocaleData.UK.getLocale());
        user.setChatState(String.valueOf(ChatState.CALM));
        usersService.saveAndFlush(user);

        GoalsEntity goal = new GoalsEntity();
        goal.setGoalName("AddGoal");
        goal.setGoalDescription("Plus one more goal");
        goal.setUser(user);
        return goal;
    }
}