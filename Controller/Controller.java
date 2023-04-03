package Controller;

import Model.Discount;
import Model.Restaurant;
import Model.Snappfood;
import Model.Users.Customer;
import Model.Users.RestaurantAdmin;
import Model.Users.SnappfoodAdmin;
import Model.Users.User;

public class Controller {
    static boolean isValidUsernameFormat(String username) {
        return username.matches("(?=.*[a-zA-Z])\\w+");
    }

    static boolean isValidPasswordFormat(String password) {
        return password.matches("\\w+");
    }

    static boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{5,}$");
    }

    static boolean isValidMenuName(String menuName) {
        return menuName.matches("customer menu|restaurant admin menu|Snappfood admin menu");
    }

    static boolean hasAccess(String menuName, User user) {
        if (user instanceof SnappfoodAdmin && menuName.equals("Snappfood admin menu")) return true;
        if (user instanceof RestaurantAdmin && menuName.equals("restaurant admin menu")) return true;
        if (user instanceof Customer && menuName.equals("customer menu")) return true;
        return false;
    }

    static boolean isValidType(String type) {
        return type.matches("[a-z-]+");
    }

    static boolean isValidCode(String code) {
        return code.matches("[a-zA-Z\\d]+");
    }

    static boolean isValidCategory(String category) {
        return category.matches("starter|entree|dessert");
    }

    static boolean isValidFoodName(String name) {
        return name.matches("[a-z-]+");
    }

    public static int getBalance() {
        return Snappfood.getCurrentUser().getBalance();
    }

    public static String showRestaurants(String type, User user) {
        StringBuilder restaurantOutput = new StringBuilder();
        int i = 1;
        if (type == null) for (Restaurant restaurant : Snappfood.getRestaurants())
            restaurantOutput.append(i++).append(user.printRestaurants(restaurant));
        else for (Restaurant restaurant : Snappfood.getRestaurants())
            if (restaurant.getType().equals(type))
                restaurantOutput.append(i++).append(user.printRestaurants(restaurant));
        return restaurantOutput.toString();
    }

    public static String showDiscounts(User user) {
        StringBuilder discountOutput = new StringBuilder();
        int i = 1;
        for (Discount discount : Snappfood.getDiscounts())
            discountOutput.append(i++).append(user.printDiscount(discount));
        return discountOutput.toString();
    }
}
