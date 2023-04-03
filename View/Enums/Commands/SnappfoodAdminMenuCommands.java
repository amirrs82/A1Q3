package View.Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SnappfoodAdminMenuCommands {
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    ADD_RESTAURANT("add\\s+restaurant\\s+(?<name>\\S+)\\s+(?<password>\\S+)\\s+(?<type>\\S+)"),
    SHOW_RESTAURANT("show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?"),
    REMOVE_RESTAURANT("remove\\s+restaurant\\s+(?<name>\\S+)"),
    SET_DISCOUNT("set\\s+discount\\s+(?<username>\\S+)\\s+(?<amount>[\\d-]+)\\s+(?<code>\\S+)"),
    SHOW_DISCOUNTS("show\\s+discounts"),
    ;
    private final String regex;

    SnappfoodAdminMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, SnappfoodAdminMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches()) return matcher;
        return null;
    }
}
