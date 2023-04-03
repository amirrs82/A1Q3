package View.Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    REGISTER("register\\s+(?<username>\\S+)\\s+(?<password>\\S+)"),
    LOGIN("login\\s+(?<username>\\S+)\\s+(?<password>\\S+)"),
    CHANGE_PASSWORD("change\\s+password\\s+(?<username>\\S+)\\s+(?<oldPassword>\\S+)\\s+(?<newPassword>\\S+)"),
    REMOVE_ACCOUNT("remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)"),
    ;
    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, LoginMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches()) return matcher;
        return null;
    }
}
