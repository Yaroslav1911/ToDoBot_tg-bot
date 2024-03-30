package edu.todobot.bot.service.actions.additionalactions;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.data.LocaleData;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.entities.UsersEntity;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class StartAction implements Actions, Configurations {

    private final UsersService usersService;

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();
        ActionsUtils.goalID = null;

        if (usersService.findById(chatId).isEmpty()) {
            var chat = msg.getChat();

            UsersEntity user = new UsersEntity();
            user.setId(chatId);
            user.setUserName(chat.getUserName());
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setLang(LocaleData.EN.getLocale());
            user.setChatState(String.valueOf(ChatState.CALM));
            usersService.saveAndFlush(user);
        }

        SendMessage sendMessage = ActionsUtils.addMainButtons(msg.getChatId());
        sendMessage.setChatId(chatId);
        sendMessage.setText(getBundle("welcome"));
        usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
        return sendMessage;
    }
}
