package edu.todobot.database.service;

import edu.todobot.database.entities.UsersEntity;
import edu.todobot.database.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public Optional<UsersEntity> findById(long chatId){
        return usersRepository.findById(chatId);
    }
    @Transactional
    public String getUserLanguage(long chatId){
        if (usersRepository.findById(chatId).isPresent()) {
            return usersRepository.getReferenceById(chatId).getLang();
        } else {
            return "null";
        }
    }
    @Transactional
    public String getUsersChatState(long chatId) {
        return usersRepository.getReferenceById(chatId).getChatState();
    }
    @Transactional
    public void setUsersChatState(String chatState, long chatId) {
        usersRepository.getReferenceById(chatId).setChatState(chatState);
    }
    @Transactional
    public void setUserLanguage(String language, long chatId) {
        usersRepository.getReferenceById(chatId).setLang(language);
    }
    @Transactional
    public void saveAndFlush(UsersEntity user){
        usersRepository.saveAndFlush(user);
    }
}
