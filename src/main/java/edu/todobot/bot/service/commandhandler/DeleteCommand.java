package edu.todobot.bot.service.commandhandler;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.service.ToDoBotEngine;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class DeleteCommand extends CommandHandler implements Configurations {
    private final GoalsService goalsService;
    private final UsersService usersService;
    private final ToDoBotEngine botEngine;
    @Override
    public void check(Update update) {
        var messageText = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();

        if (getBundle("EGB_delete").equalsIgnoreCase(messageText) &&
                usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.GOAL_EDITING))){
            usersService.setUsersChatState(String.valueOf(ChatState.CALM), chatId);
            goalsService.deleteGoalById(ActionsUtils.goalID);
            ActionsUtils.goalID = null;
            SendMessage sendMessage = ActionsUtils.addMainButtons(update.getMessage().getChatId());
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("TestBot_delete"));
            botEngine.send(sendMessage);
        }

        checkNext(update);
    }
}
