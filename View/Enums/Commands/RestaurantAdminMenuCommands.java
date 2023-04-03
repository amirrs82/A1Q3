package View.Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestaurantAdminMenuCommands {
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    CHARGE_ACCOUNT("charge\\s+account\\s+(?<amount>-?\\d+)"),
    SHOW_BALANCE("show\\s+balance"),
    ADD_FOOD("add\\s+food\\s+(?<name>\\S+)\\s+(?<category>\\S+)\\s+(?<price>-?\\d+)\\s+(?<cost>-?\\d+)"),
    REMOVE_FOOD("remove\\s+food\\s+(?<name>\\S+)");
    private final String regex;

    RestaurantAdminMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, RestaurantAdminMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches()) return matcher;
        return null;
    }
}
