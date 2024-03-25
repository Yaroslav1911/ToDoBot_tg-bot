package edu.todobot.bot.config;

import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationsTest implements Configurations{


    @Test
    void bindingByTest() {
        bindingBy().put("testKey", "testValue");

        assertEquals( "testValue", bindingBy().get("testKey"));
        assertEquals(1, bindingBy().size());

        bindingBy().remove("testKey");
        assertEquals(0, bindingBy().size());
    }

    @Test
    void getBundleUATest() {
        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[] {"uk", "UA"}));

        String expected = "Введіть опис до задачі";
        String actual = getBundle("AGD_writeDescription");

        assertEquals(expected, actual);
    }

    @Test
    void getBundleENTest() {
        BotConfig.bundle = ResourceBundle.getBundle("lang", BotConfig.setLocale(new String[] {"", ""}));

        String expected = "Write some description of a goal";
        String actual = getBundle("AGD_writeDescription");

        assertEquals(expected, actual);
    }
}