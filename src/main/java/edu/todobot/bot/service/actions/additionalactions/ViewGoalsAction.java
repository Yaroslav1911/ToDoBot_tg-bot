package edu.todobot.bot.service.actions.additionalactions;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.dto.GoalsDto;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ViewGoalsAction implements Actions, Configurations {

    private final GoalsService goalsService;
    private final UsersService usersService;

    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.CALM))) {
            return new SendMessage(chatId.toString(), getBundle("E_wrongChatState"));
        }

        if(goalsService.findAllGoalsByUserId(chatId).isEmpty()){
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("noGoals"));
            return sendMessage;
        } else {
            var goals = goalsService.findAllGoalsByUserId(chatId);
            List<GoalsDto> list = new ArrayList<>(goals);
            String out = list.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",","");

            return new SendMessage(chatId.toString(), out);
        }
    }
}
