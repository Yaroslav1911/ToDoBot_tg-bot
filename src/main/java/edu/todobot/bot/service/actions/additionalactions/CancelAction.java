package edu.todobot.bot.service.actions.additionalactions;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class CancelAction implements Actions, Configurations {
    private final UsersService usersService;
    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId().toString();
        ActionsUtils.goalID = null;
        usersService.setUsersChatState(String.valueOf(ChatState.CALM), Long.parseLong(chatId));

        SendMessage sendMessage = ActionsUtils.addMainButtons(msg.getChatId());
        sendMessage.setChatId(chatId);
        sendMessage.setText(getBundle("whatNext"));
        return sendMessage;
    }
}
