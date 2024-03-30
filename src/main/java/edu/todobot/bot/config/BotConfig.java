package edu.todobot.bot.config;

import edu.todobot.bot.data.ChatState;
import edu.todobot.bot.data.LocaleData;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@EnableScheduling
@Configuration
@Data
public class BotConfig {
    public static ChatState chatState;
    public static Map<String, String> bindingBy = new ConcurrentHashMap<>();
    private static final Map<String, List<String>> commandMap = new HashMap<>();
    private static final String[] LOCALE_DEFAULT = {"", ""};
    private static final String[] LOCALE_UK_UA = {"uk", "UA"};

    public static ResourceBundle bundle;
    public static Locale setLocale(String[] locale) {
        return new Locale.Builder().setLanguage(locale[0]).setRegion(locale[1]).build();
    }
    public static void setLanguage(String [] locale){
        bundle = ResourceBundle.getBundle(
                LocaleData.RESOURCE_BUNDLE_NAME.getLocale(),
                setLocale(locale));
    }

    static {
        Stream.of(LOCALE_DEFAULT, LOCALE_UK_UA).forEach(loc -> {
            ResourceBundle lang = ResourceBundle.getBundle(
                    LocaleData.RESOURCE_BUNDLE_NAME.getLocale(),
                    setLocale(loc));
            lang.getKeys().asIterator().forEachRemaining(key -> {
                String string = lang.getString(key);
                List<String> orDefault = commandMap.getOrDefault(key, new ArrayList<>());
                orDefault.add(string);
                commandMap.put(key, orDefault);
            });
        });
    }

    public static List<String> getCommand(String key) {
        return commandMap.get(key);
    }
    public static String[] getLocaleDefault() {
        return LOCALE_DEFAULT;
    }
    public static String[] getLocaleUkUa() {
        return LOCALE_UK_UA;
    }

    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String token;

}