package edu.todobot.database.entities;

import edu.todobot.bot.config.BotConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goals", schema = "public")
public class GoalsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "goal_id")
    @NonNull private UUID id;

    @Column(name = "goal_name")
    @NonNull private String goalName;

    @Column(name = "goal_description")
    private String goalDescription;

    @Column(name = "goal_deadline")
    private LocalDateTime goalDeadline;

    @Column(name = "goal_reminder")
    private LocalDateTime goalReminder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @BatchSize(size = 20)
    private UsersEntity user;

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public void setGoalDeadline(LocalDateTime goalDeadline) {
        this.goalDeadline = goalDeadline;
    }

    public void setGoalReminder(LocalDateTime goalReminder) {
        this.goalReminder = goalReminder;
    }

    @Override
    public String toString() {
        return String.format(BotConfig.bundle.getString("GE_toString"),
                goalName, goalDescription, goalDeadline, goalReminder);
    }
}
