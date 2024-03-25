package edu.todobot.bot.config;

import edu.todobot.bot.data.CommandsData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BotConfigTest {

    private static final String[] LOCALE_DEFAULT = {"", ""};
    private static final String[] LOCALE_UK_UA = {"uk", "UA"};

    @Test
    void setLocaleTest() {
        ResourceBundle bundleDefault = ResourceBundle.getBundle("lang",
                BotConfig.setLocale(LOCALE_DEFAULT));
        ResourceBundle bundleUA = ResourceBundle.getBundle("lang",
                BotConfig.setLocale(LOCALE_UK_UA));

        Locale localeDef = new Locale.Builder().setLanguage("").setRegion("").build();
        Locale localeUA = new Locale.Builder().setLanguage("uk").setRegion("UA").build();

        assertEquals(localeDef, bundleDefault.getLocale());
        assertEquals(localeUA, bundleUA.getLocale());
    }

    @Test
    void setLanguageENTest() {
        BotConfig.setLanguage(LOCALE_DEFAULT);

        Locale actual = BotConfig.bundle.getLocale();
        Locale expected = BotConfig.setLocale(LOCALE_DEFAULT);

        assertEquals(expected, actual);
    }

    @Test
    void setLanguageUATest() {
        BotConfig.setLanguage(LOCALE_UK_UA);
        Locale expected = BotConfig.setLocale(LOCALE_UK_UA);

        Locale actual = BotConfig.bundle.getLocale();

        assertEquals(expected, actual);
    }

    @Test
    void getCommandTest() {
        List<String> firstExpected = List.of("View goals", "Подивитися задачі");
        var firstActual = BotConfig.getCommand(CommandsData.VIEW_GOALS.getCommand());

        List<String> secondExpected = List.of("Add a remind date", "Додати нагадування");
        var secondActual = BotConfig.getCommand("AGDl_addReminder");

        assertEquals(firstExpected, firstActual);
        assertEquals(secondExpected, secondActual);
    }
}