package edu.todobot.bot.service.timerservice;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.service.ToDoBotEngine;
import edu.todobot.database.dto.GoalsDto;
import edu.todobot.database.service.GoalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class Scheduler implements Configurations {
    private final GoalsService goalsService;
    private final ToDoBotEngine bot;

    @Scheduled(cron = "0 * * * * *")
    private void scheduler() {
        var goals = goalsService.findAllGoals();
        String now = fromDateToString(LocalDateTime.now());


        for (GoalsDto goal : goals) {
            if (Objects.nonNull(goal.goalReminder())) {

                String reminderDate = fromDateToString(goal.goalReminder());

                if (Objects.equals(reminderDate, now)) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(goalsService.getUserIdByReminder(goal.goalReminder()));
                    sendMessage.setText(getBundle("TS_remind") + goal);
                    bot.send(sendMessage);
                }
            }
        }
    }
    private String fromDateToString(LocalDateTime ldt){
        DateTimeFormatter TO_STRING_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return ldt.format(TO_STRING_FORMATTER);
    }
}
