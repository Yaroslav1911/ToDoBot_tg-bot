package edu.todobot.bot.utils;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.data.CommandsData;
import edu.todobot.database.dto.GoalsDto;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
public class ActionsUtils {
    public static UUID goalID;
    public static Map<String, String> goalSet = new HashMap<>();
    public static InputFile getRandomSticker(List<InputFile> stickers){
        Random rnd = new Random();
        return stickers.get(rnd.nextInt(stickers.size()));
    }
    private static String bundle(String key) {
        return BotConfig.bundle.getString(key);
    }
    public static String getStringTextOfGoalToChange(GoalsDto goal) {
        return String.format(bundle("stringFormatToChange"),
                goal.goalName(),
                goal.goalDescription(),
                replaceT(goal.goalDeadline()),
                replaceT(goal.goalReminder()));
    }
    public static String getStringTextOfGoal(GoalsDto goal) {
        return String.format(bundle("GE_toString"),
                goal.goalName(),
                goal.goalDescription(),
                replaceT(goal.goalDeadline()),
                replaceT(goal.goalReminder()));
    }
    private static String replaceT (LocalDateTime goalReminder) {
        if (goalReminder == null) {
            return "null";
        }
        return goalReminder.toString().replace("T", " ");
    }
    public static String getStringFromMapOfGoal() {
        return String.format(bundle("stringFormatGoalAdded"),
                ActionsUtils.goalSet.get("goalName"), ActionsUtils.goalSet.get("description"),
                ActionsUtils.goalSet.get("deadline"), ActionsUtils.goalSet.get("reminder"));
    }
    public static LocalDateTime formatterFromStringToLocalDateTime(String string) {
        String[] text = string.split("[-: ]");

        if (Integer.parseInt(text[3]) < 10) {
            String am = "0";
            text[3] = am.concat(text[3]);
        }

        String date = "";
        if (text.length == 5) {
            date = String.format("20%s-%s-%sT%s:%s", text[0], text[1], text[2], text[3], text[4]);
        } else if (text.length == 4) {
            date = String.format("20%s-%s-%sT%s:00", text[0], text[1], text[2], text[3]);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(date, dtf);
    }
    public static SendMessage addMainButtons(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowsList = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(bundle("addGoal"));
        row1.add(bundle("hi"));
        keyboardRowsList.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(bundle("viewGoals"));
        row2.add(bundle("editGoals"));
        keyboardRowsList.add(row2);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(bundle(CommandsData.CHANGE_LANGUAGE.getCommand()));
        keyboardRowsList.add(row3);

        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
    public static SendMessage addNextStepWithThreeButtons(long chatId, String btnText1, String btnText2, String btnText3){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        row1.add(btnText1);
        row1.add(btnText2);

        keyboardRowsList.add(row1);

        KeyboardRow row2 = new KeyboardRow();

        row2.add(btnText3);

        keyboardRowsList.add(row2);

        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
    public static SendMessage addNextStepWithOneButton(long chatId, String btnText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRowsList = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();

        row1.add(btnText);

        keyboardRowsList.add(row1);

        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
    public static SendMessage addEditGoalDataButtons(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        row1.add(bundle("EGB_changeName"));
        row1.add(bundle("EGB_changeDescription"));

        keyboardRowsList.add(row1);

        KeyboardRow row2 = new KeyboardRow();

        row2.add(bundle("EGB_changeDeadline"));
        row2.add(bundle("EGB_changeReminder"));

        keyboardRowsList.add(row2);

        KeyboardRow row3 = new KeyboardRow();

        row3.add(bundle("EGB_delete"));

        keyboardRowsList.add(row3);

        KeyboardRow row4 = new KeyboardRow();

        row4.add(bundle("EGB_done"));

        keyboardRowsList.add(row4);

        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
