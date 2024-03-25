package edu.todobot.bot.utils;

import edu.todobot.bot.config.BotConfig;
import edu.todobot.bot.service.ToDoBotEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionsUtilsTest {

    @Mock
    private ToDoBotEngine bot = Mockito.mock(ToDoBotEngine.class);
    private static final List<InputFile> inputFileList = List.of(
            new InputFile("CAACAgIAAxkBAAELUCxlv0jLJExm77WtBxekJdm1636PhAACQhAAAjPFKUmQDtQRpypKgjQE")
    );

    @Test
    void getRandomStickerTest() {
        assertEquals(new InputFile("CAACAgIAAxkBAAELUCxlv0jLJExm77WtBxekJdm1636PhAACQhAAAjPFKUmQDtQRpypKgjQE"),
                ActionsUtils.getRandomSticker(inputFileList));
    }

    @ParameterizedTest(name = "{0} - EN bundle test")
    @CsvFileSource(resources = "/testingData/BundleTestEN.csv", numLinesToSkip = 1)
    void getBundle_ENTest(String command, String expectedText) {
        ResourceBundle bundle = ResourceBundle.getBundle("lang",
                BotConfig.setLocale(new String[]{"", ""}));

        assertEquals(bundle.getString(command), expectedText);
    }

    @ParameterizedTest(name = "{0} - UA bundle test")
    @CsvFileSource(resources = "/testingData/BundleTestUA.csv", numLinesToSkip = 1)
    void getBundle_UATest(String command, String expectedText) {
        ResourceBundle bundle = ResourceBundle.getBundle("lang",
                BotConfig.setLocale(new String[]{"uk", "UA"}));

        assertEquals(bundle.getString(command), expectedText);
    }

    @Test
    void getStringTextOfGoalUATest() {

    }

    @Test
    void getStringTextOfGoalENTest() {

    }


    @Test
    void getStringFromMapOfGoalENTest() {
        var set = ActionsUtils.goalSet;
        set.put("goalName", "testName");
        set.put("description", "null");
        set.put("deadline", "2024-06-04 18:22:00.0");
        set.put("reminder", "null");

        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[]{"", ""}));
        String actual = ActionsUtils.getStringFromMapOfGoal();
        String expected = getExpectedText_for_getStringFromMapOfGoalTest(set);
        set.clear();

        assertEquals(expected, actual);
    }

    @Test
    void getStringFromMapOfGoalUATest() {
        var set = ActionsUtils.goalSet;
        set.put("goalName", "testName");
        set.put("description", "null");
        set.put("deadline", "2024-06-04 18:22:00.0");
        set.put("reminder", "null");

        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[]{"uk", "UA"}));
        String actual = ActionsUtils.getStringFromMapOfGoal();
        String expected = getExpectedText_for_getStringFromMapOfGoalTest(set);
        set.clear();

        assertEquals(expected, actual);
    }

    @Test
    void formatterFromStringToLocalDateTimeTest() {
        String date = "24-06-04 18:22";

        LocalDateTime actual = ActionsUtils.formatterFromStringToLocalDateTime(date);

        LocalDateTime expected = LocalDateTime.of(2024, 6, 4, 18, 22);

        assertEquals(expected, actual);
    }

    @Test
    void formatterFromStringToLocalDateTime_WithoutMinutesTest() {
        String date = "24-06-04 18";

        LocalDateTime actual = ActionsUtils.formatterFromStringToLocalDateTime(date);

        LocalDateTime expected = LocalDateTime.of(2024, 6, 4, 18, 0);

        assertEquals(expected, actual);
    }

    @Test
    void addMainButtonsENTest() throws TelegramApiException {
        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[]{"", ""}));
        bot.execute(
                ActionsUtils.addMainButtons(1));
        Mockito.verify(bot).execute(
                ActionsUtils.addMainButtons(1));
    }

    @Test
    void addMainButtonsUATest() throws TelegramApiException {
        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[]{"uk", "UA"}));
        bot.execute(
                ActionsUtils.addMainButtons(1));
        Mockito.verify(bot).execute(
                ActionsUtils.addMainButtons(1));
    }

    @Test
    void addNextStepWithThreeButtonsTest() throws TelegramApiException {
        bot.execute(
                ActionsUtils.addNextStepWithThreeButtons(1, "btn1", "btn2", "btn3"));
        Mockito.verify(bot).execute(
                ActionsUtils.addNextStepWithThreeButtons(1, "btn1", "btn2", "btn3"));
    }

    @Test
    void addNextStepWithOneButtonTest() throws TelegramApiException {
        bot.execute(
                ActionsUtils.addNextStepWithOneButton(1, "btn1"));
        Mockito.verify(bot).execute(
                ActionsUtils.addNextStepWithOneButton(1, "btn1"));
    }

    @Test
    void addEditGoalDataButtonsENTest() throws TelegramApiException {
        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[]{"", ""}));
        bot.execute(
                ActionsUtils.addEditGoalDataButtons(1));
        Mockito.verify(bot).execute(
                ActionsUtils.addEditGoalDataButtons(1));
    }

    @Test
    void addEditGoalDataButtonsUATest() throws TelegramApiException {
        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[]{"uk", "UA"}));
        bot.execute(
                ActionsUtils.addEditGoalDataButtons(1));
        Mockito.verify(bot).execute(
                ActionsUtils.addEditGoalDataButtons(1));
    }

    private static String getExpectedText_for_getStringFromMapOfGoalTest(Map<String, String> set) {
        if (BotConfig.bundle.getLocale().equals(BotConfig.setLocale(new String[]{"", ""}))) {
            return String.format("""
                    Goal successfully added✅
                    Name: %s\s
                    Description: %s\s
                    Deadline: %s\s
                    Reminder: %s""",
                    set.get("goalName"), set.get("description"), set.get("deadline"), set.get("reminder"));
        }

        if (BotConfig.bundle.getLocale().equals(BotConfig.setLocale(new String[]{"uk", "UA"}))) {
            return String.format("""
                    Задача успішно додана✅
                    Назва: %s\s
                    Опис: %s\s
                    Крайній термін: %s\s
                    Нагадування: %s""",
                    set.get("goalName"), set.get("description"), set.get("deadline"), set.get("reminder"));
        }

        return "No such lang";
    }
    private static String getExpectedText_for_getStringTextOfGoalTest(String goal) {
        String[] splitText = goal.split(",");

        if (BotConfig.bundle.getLocale().equals(BotConfig.setLocale(new String[]{"", ""}))) {
            return String.format("""
                    What you want to change?\s

                    Goal_name:             \s%s\s
                    Goal_description:   \s%s\s
                    Goal_deadline:        \s%s\s
                    Goal_reminder:       \s%s""", splitText[0], splitText[1], splitText[2], splitText[3]);
        }

        if (BotConfig.bundle.getLocale().equals(BotConfig.setLocale(new String[]{"uk", "UA"}))) {
            return String.format("""
                Що ви хочете змінити?\s

                Назва_задачі:       \s %s\s
                Опис_задачі:          \s%s\s
                Крайній_термін:   \s %s\s
                Нагадування:         \s%s""", splitText[0], splitText[1], splitText[2], splitText[3]);
        }

        return "No such lang";
    }
}