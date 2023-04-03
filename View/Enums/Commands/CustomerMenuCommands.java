package View.Enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CustomerMenuCommands {
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    CHARGE_ACCOUNT("charge\\s+account\\s+(?<amount>-?\\d+)"),
    SHOW_BALANCE("show\\s+balance"),
    SHOW_RESTAURANT("show\\s+restaurant(\\s+-t\\s+(?<type>\\S+))?"),
    SHOW_MENU("show\\s+menu\\s+(?<restaurantName>\\S+)(\\s+-c\\s+(?<category>\\S+))?"),
    ADD_TO_CART("add\\s+to\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)(\\s+-n\\s+(?<number>-?\\d+))?"),
    REMOVE_FROM_CART("remove\\s+from\\s+cart\\s+(?<restaurantName>\\S+)\\s+(?<foodName>\\S+)(\\s+-n\\s+(?<number>-?\\d+))?"),
    SHOW_CART("show\\s+cart"),
    SHOW_DISCOUNTS("show\\s+discounts"),
    PURCHASE_CART("purchase\\s+cart(\\s+-d\\s+(?<discountCode>\\S+))?");
    private final String regex;

    CustomerMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, CustomerMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches()) return matcher;
        return null;
    }
}
