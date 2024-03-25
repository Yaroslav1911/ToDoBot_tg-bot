package edu.todobot.bot.data;

public enum CommandsData {
    ADD_GOAL("AK_addGoal"),
    ADD_DESCRIPTION("AK_addDescription"),
    NO_NEED_DESCRIPTION("AK_noNeedDescription"),
    ADD_DEADLINE("AK_addDeadline"),
    NO_NEED_DEADLINE("AK_noNeedDeadline"),
    ADD_REMINDER("AK_addReminder"),
    NO_NEED_REMINDER("AK_noNeedReminder"),
    VIEW_GOALS("AK_viewGoals"),
    EDIT_GOALS("AK_editGoals"),
    CANCEL("AK_cancel"),
    DONE("AK_done"),
    CHANGE_NAME("AK_changeName"),
    CHANGE_DESCRIPTION("AK_changeDescription"),
    CHANGE_DEADLINE("AK_changeDeadline"),
    CHANGE_REMINDER("AK_changeReminder"),
    CHANGE_LANGUAGE("AK_changeLang"),
    START("AK_start"),
    HI_COMMAND("/hi");

    private final String command;

    CommandsData(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
