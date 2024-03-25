package edu.todobot.bot.service.actions.additionalactions;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.service.actions.interfaces.Actions;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ChangeLanguageAction implements Actions, Configurations {
    private final UsersService usersService;
    @Override
    public BotApiMethod handle(Update update) {
        var msg = update.getMessage();
        var chatId = msg.getChatId();

        if (!usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.CALM))) {
            return new SendMessage(chatId.toString(), getBundle("CLA_changeLang"));
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getBundle("CLA_message"));
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        InlineKeyboardButton btnEN = new InlineKeyboardButton();
        btnEN.setText(getBundle("CLA_english"));
        btnEN.setCallbackData("english_lang_btn");
        rowInLine.add(btnEN);

        InlineKeyboardButton btnUA = new InlineKeyboardButton();
        btnUA.setText(getBundle("CLA_ukraine"));
        btnUA.setCallbackData("ukrainian_lang_btn");
        rowInLine.add(btnUA);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInLine);

        return sendMessage;
    }

}
