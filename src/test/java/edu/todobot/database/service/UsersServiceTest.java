package edu.todobot.database.service;

import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.data.LocaleData;
import edu.todobot.database.entities.UsersEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    void findByIdTest() {
        Optional<UsersEntity> found = usersService.findById(12313L);

        assertThat(found).isPresent();
    }

    @Test
    void getUserLanguageTest() {
        String userLang = usersService.getUserLanguage(2851L);

        assertThat(userLang).isEqualTo(LocaleData.EN.getLocale());
        assertThat(userLang).isNotEqualTo(LocaleData.UK.getLocale());
    }

    @Test
    void getUsersChatStateTest() {
        String userChatState = usersService.getUsersChatState(2851L);

        assertThat(userChatState).isEqualTo(String.valueOf(ChatState.CALM));
    }

    @Test
    void setUsersChatStateTest() {
        usersService.setUsersChatState(String.valueOf(ChatState.GOAL_EDITING), 38818L);
        String userChatState = usersService.getUsersChatState(38818L);
        assertThat(userChatState).isEqualTo(String.valueOf(ChatState.GOAL_EDITING));

        usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_NAME), 38818L);
        String userChatState2 = usersService.getUsersChatState(38818L);
        assertThat(userChatState2).isEqualTo(String.valueOf(ChatState.AWAITING_NAME));
    }

    @Test
    void setUserLanguageTest() {
        usersService.setUserLanguage(LocaleData.UK.getLocale(), 4233L);
        String userLang = usersService.getUserLanguage(4233L);
        assertThat(userLang).isEqualTo(LocaleData.UK.getLocale());

        usersService.setUserLanguage(LocaleData.EN.getLocale(), 4233L);
        String userLang2 = usersService.getUserLanguage(4233L);
        assertThat(userLang2).isEqualTo(LocaleData.EN.getLocale());
    }

    @Test
    void saveAndFlushTest() {
        UsersEntity user = new UsersEntity();
        user.setId(111111L);
        user.setUserName("usernameTest");
        user.setLang(LocaleData.EN.getLocale());
        user.setChatState(String.valueOf(ChatState.CALM));

        usersService.saveAndFlush(user);

        Optional<UsersEntity> found = usersService.findById(111111L);

        assertThat(found).isPresent();
    }
}