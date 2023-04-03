package View.Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    ENTER_MENU("enter\\s+(?<menuName>.+)"),

    ;
    private final String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, MainMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches()) return matcher;
        return null;
    }
}
