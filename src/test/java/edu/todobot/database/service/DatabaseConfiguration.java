package edu.todobot.database.service;

import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.data.LocaleData;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.entities.GoalsEntity;
import edu.todobot.database.entities.UsersEntity;
import edu.todobot.database.repository.GoalsRepository;
import edu.todobot.database.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;

@Configuration
public class DatabaseConfiguration {
    private static final PostgreSQLContainer databaseInstance;

    static {
        databaseInstance = new PostgreSQLContainer("postgres:15.5-alpine3.19")
                .withDatabaseName   (DatabaseCredentials.DATABASE_NAME.getValue())
                .withUsername       (DatabaseCredentials.USERNAME.getValue())
                .withPassword       (DatabaseCredentials.PASSWORD.getValue());
        databaseInstance.start();

        System.setProperty("spring.datasource.url",         databaseInstance.getJdbcUrl());
        System.setProperty("spring.datasource.username",    databaseInstance.getUsername());
        System.setProperty("spring.datasource.password",    databaseInstance.getPassword());

        System.setProperty("spring.flyway.url",         databaseInstance.getJdbcUrl());
        System.setProperty("spring.flyway.user",        databaseInstance.getUsername());
        System.setProperty("spring.flyway.password",    databaseInstance.getPassword());
    }

    @Autowired
    public void init(UsersRepository usersRepository, GoalsRepository goalsRepository) {
        UsersEntity user1 = new UsersEntity();
        user1.setId(12313L);
        user1.setUserName("testing");
        user1.setChatState(String.valueOf(ChatState.CALM));
        user1.setLang(LocaleData.EN.getLocale());
        usersRepository.saveAndFlush(user1);

        UsersEntity user2 = new UsersEntity();
        user2.setId(2851L);
        user2.setUserName("secondUser");
        user2.setChatState(String.valueOf(ChatState.CALM));
        user2.setLang(LocaleData.EN.getLocale());
        usersRepository.saveAndFlush(user2);

        UsersEntity user3 = new UsersEntity();
        user3.setId(38818L);
        user3.setUserName("thirdUser");
        user3.setChatState(String.valueOf(ChatState.CALM));
        user3.setLang(LocaleData.EN.getLocale());
        usersRepository.saveAndFlush(user3);

        UsersEntity user4 = new UsersEntity();
        user4.setId(4233L);
        user4.setUserName("fourthUser");
        user4.setChatState(String.valueOf(ChatState.CALM));
        user4.setLang(LocaleData.EN.getLocale());
        usersRepository.saveAndFlush(user4);

        UsersEntity user5 = new UsersEntity();
        user5.setId(55554L);
        user5.setUserName("fifthUser");
        user5.setChatState(String.valueOf(ChatState.CALM));
        user5.setLang(LocaleData.EN.getLocale());
        usersRepository.saveAndFlush(user5);

        UsersEntity user6 = new UsersEntity();
        user6.setId(5555666L);
        user6.setUserName("sixthUser");
        user6.setChatState(String.valueOf(ChatState.CALM));
        user6.setLang(LocaleData.EN.getLocale());
        usersRepository.saveAndFlush(user6);


        GoalsEntity goal1 = new GoalsEntity();
        goal1.setGoalName("firstGoal");
        goal1.setUser(user5);
        LocalDateTime ldt = ActionsUtils.formatterFromStringToLocalDateTime("24-03-26 18:31");
        goal1.setGoalReminder(ldt);
        goalsRepository.saveAndFlush(goal1);

        GoalsEntity goal2 = new GoalsEntity();
        goal2.setGoalName("secondGoal");
        goal2.setUser(user5);
        goalsRepository.saveAndFlush(goal2);

        GoalsEntity goal3 = new GoalsEntity();
        goal3.setGoalName("thirdGoal");
        goal3.setUser(user6);
        goalsRepository.saveAndFlush(goal3);

        GoalsEntity goal4 = new GoalsEntity();
        goal4.setGoalName("fourthGoal");
        goal4.setGoalDescription("Testing finById method.//");
        goal4.setUser(user6);
        goalsRepository.saveAndFlush(goal4);

        GoalsEntity goal5 = new GoalsEntity();
        goal5.setGoalName("forFindById");
        goal5.setGoalDescription("Testing finById method.//");
        goal5.setUser(user6);
        goalsRepository.saveAndFlush(goal5);
    }
}
