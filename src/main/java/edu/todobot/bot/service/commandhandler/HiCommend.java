package edu.todobot.bot.service.commandhandler;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.bot.data.StickerData;
import edu.todobot.bot.service.ToDoBotEngine;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
public class HiCommend extends CommandHandler implements Configurations {
    private final UsersService usersService;
    private final ToDoBotEngine botEngine;
    @Override
    public void check(Update update) {
        var msg = update.getMessage();
        var text = msg.getText();
        var chatId = msg.getChatId();

        if (text.equals(CommandsData.HI_COMMAND.getCommand()) &&
                usersService.getUsersChatState(chatId).equalsIgnoreCase(String.valueOf(ChatState.CALM))) {
            try {
                botEngine.execute(new SendSticker(
                        chatId.toString(),
                        ActionsUtils.getRandomSticker(StickerData.stickers)
                ));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        checkNext(update);
    }
}
