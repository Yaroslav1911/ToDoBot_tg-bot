package edu.todobot.bot.data;

public enum LocaleData {
    RESOURCE_BUNDLE_NAME("lang"),
    EN("en"),
    UK("uk");

    private final String locale;

    LocaleData(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
