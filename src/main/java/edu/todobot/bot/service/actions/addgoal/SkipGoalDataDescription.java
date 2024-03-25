package edu.todobot.bot.service.actions.addgoal;

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
public class SkipGoalDataDescription implements Actions, Configurations {
    private final UsersService usersService;
    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.AWAITING_DESCRIPTION))) {
            usersService.setUsersChatState(String.valueOf(ChatState.AWAITING_DEADLINE), chatId);
            SendMessage sendMessage = ActionsUtils.addNextStepWithThreeButtons(chatId,
                    getBundle("AGD_addDeadline"),
                    getBundle("AGD_noNeedDeadline"),
                    getBundle("AGD_cancel"));
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("SGD_skip"));
            return sendMessage;

        } else {
            return new SendMessage(chatId.toString(), getBundle("SGD_someError"));
        }
    }
}
