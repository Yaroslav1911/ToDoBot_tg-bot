package edu.todobot.bot.config;

import java.util.Map;

public interface Configurations {

    default Map<String, String> bindingBy() {
        return BotConfig.bindingBy;
    }
    default String getBundle(String key){
        return BotConfig.bundle.getString(key);
    }
}
