package edu.todobot.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "goals")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class UsersEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "bot_localization")
    private String lang;

    @Column(name = "chat_state")
    private String chatState;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    @BatchSize(size = 20)
    private Set<GoalsEntity> goals;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setChatState(String chatState) {
        this.chatState = chatState;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lang='" + lang + '\'' +
                ", chatState='" + chatState + '\'' +
                ", goals=" + goals +
                '}';
    }
}
