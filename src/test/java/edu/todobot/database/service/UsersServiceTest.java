package edu.todobot.database.service;

import edu.todobot.database.entities.UsersEntity;
import edu.todobot.database.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UsersServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void findById() {
        UsersEntity user = new UsersEntity();
        user.setId(12313L);
        user.setUserName("testing");
        entityManager.persist(user);
        entityManager.flush();

        UsersEntity found = usersRepository.findById(user.getId()).orElse(null);

        assertThat(found.getUserName()).isEqualTo(user.getUserName());
    }

    @Test
    void getUserLanguage() {
    }

    @Test
    void getUsersChatState() {
    }

    @Test
    void setUsersChatState() {
    }

    @Test
    void setUserLanguage() {
    }

    @Test
    void saveAndFlush() {

    }
}