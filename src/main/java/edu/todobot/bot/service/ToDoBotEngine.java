package edu.todobot.bot.service;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.data.ActionsData;
import edu.todobot.bot.data.LocaleData;
import edu.todobot.bot.service.commandhandler.*;
import edu.todobot.bot.utils.ActionsUtils;
import edu.todobot.database.service.GoalsService;
import edu.todobot.database.service.UsersService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ResourceBundle;
@Component
public class ToDoBotEngine extends TelegramLongPollingBot implements Configurations {
    final BotConfig config;
    private final GoalsService goalsService;
    private final UsersService usersService;
    private final ActionsData actionsData;

    public ToDoBotEngine(BotConfig config, GoalsService goalsService,
                         UsersService usersService, ActionsData actionsData) {
        super(config.getToken());
        this.config = config;
        this.goalsService = goalsService;
        this.usersService = usersService;
        this.actionsData = actionsData;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null) {
            chooseBundleByUsersLang(update);
        }

        if(update.hasMessage()) {
            commandsChain(update);
        }

        if (update.hasCallbackQuery()) {
            hasCallbackQuery(update);
        }
    }
    private void chooseBundleByUsersLang(Update update) {
        String[] locale;
        String lang = usersService.getUserLanguage(update.getMessage().getChatId());
        if (LocaleData.UK.getLocale().equals(lang)) {
            locale = BotConfig.getLocaleUkUa();
        } else {
            locale = BotConfig.getLocaleDefault();
        }

        BotConfig.bundle = ResourceBundle.getBundle(
                LocaleData.RESOURCE_BUNDLE_NAME.getLocale(),
                BotConfig.setLocale(locale));
    }
    private void commandsChain(Update update) {
        CommandHandler  first =     new ActionCommands(actionsData, this),
                        second =    new RequestActionCommands(actionsData, this),
                        third =     new DeleteCommand(goalsService, usersService, this),
                        fourth =    new HiCommend(usersService,this);

        first.setNext(second)
             .setNext(third)
             .setNext(fourth);

        first.check(update);
    }
    private void hasCallbackQuery(Update update) {
            var callData = update.getCallbackQuery().getData();
            var chatId = update.getCallbackQuery().getMessage().getChatId();

            checkAndSetLanguage(callData, chatId);
    }
    private void checkAndSetLanguage(String callData, Long chatId) {
        switch (callData) {
            case "english_lang_btn" -> {
                String[] locale = BotConfig.getLocaleDefault();
                setLanguage(locale[0], locale[1], chatId, LocaleData.EN.getLocale());
            }
            case "ukrainian_lang_btn" -> {
                String[] locale = BotConfig.getLocaleUkUa();
                setLanguage(locale[0], locale[1], chatId, LocaleData.UK.getLocale());
            }
        }
    }

    private void setLanguage(String x, String x1, Long chatId, String en) {
        if (BotConfig.bundle.getLocale().equals(BotConfig.setLocale(new String[]{x, x1}))) {
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("CLA_already"));
            send(sendMessage);
        } else {
            BotConfig.setLanguage(new String[]{x, x1});
            SendMessage sendMessage = ActionsUtils.addMainButtons(chatId);
            sendMessage.setChatId(chatId);
            sendMessage.setText(getBundle("CLA_changed"));
            usersService.setUserLanguage(en, chatId);
            send(sendMessage);
        }
    }

    @SuppressWarnings("unchecked")
    public void send(BotApiMethod msg){
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
}
