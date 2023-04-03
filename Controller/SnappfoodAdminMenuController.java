package Controller;

import Model.Discount;
import Model.Restaurant;
import Model.Snappfood;
import Model.Users.Customer;
import Model.Users.RestaurantAdmin;
import Model.Users.User;
import View.Enums.Messages.SnappfoodAdminMenuMessages;

public class SnappfoodAdminMenuController {

    public static SnappfoodAdminMenuMessages checkAddRestaurant(String name, String password, String type) {
        User user = Snappfood.getUserByUsername(name);
        if (user == null)
            if (Controller.isValidUsernameFormat(name))
                if (Controller.isValidPasswordFormat(password))
                    if (Controller.isStrongPassword(password))
                        if (Controller.isValidType(type)) {
                            new Restaurant(name, type, new RestaurantAdmin(name, password));
                            return SnappfoodAdminMenuMessages.SUCCESS;
                        } else return SnappfoodAdminMenuMessages.INVALID_TYPE_FORMAT;
                    else return SnappfoodAdminMenuMessages.WEAK_PASSWORD;
                else return SnappfoodAdminMenuMessages.INVALID_PASSWORD_FORMAT;
            else return SnappfoodAdminMenuMessages.INVALID_USERNAME_FORMAT;
        else return SnappfoodAdminMenuMessages.USERNAME_EXIST;
    }

    public static SnappfoodAdminMenuMessages checkRemoveRestaurant(String name) {
        Restaurant restaurant = Snappfood.getRestaurantByName(name);
        if (restaurant != null) {
            Snappfood.removeRestaurant(restaurant);
            Snappfood.removeUser(restaurant.getOwner());
            return SnappfoodAdminMenuMessages.SUCCESS;
        } else return SnappfoodAdminMenuMessages.USERNAME_NOT_EXIST;
    }

    public static SnappfoodAdminMenuMessages checkSetDiscount(String username, int amount, String code) {
        User user = Snappfood.getUserByUsername(username);
        if (user instanceof Customer)
            if (amount > 0)
                if (Controller.isValidCode(code)) {
                    ((Customer) user).addDiscount(new Discount(code, amount, (Customer) user));
                    return SnappfoodAdminMenuMessages.SUCCESS;
                } else return SnappfoodAdminMenuMessages.INVALID_CODE_FORMAT;
            else return SnappfoodAdminMenuMessages.INVALID_AMOUNT;
        else return SnappfoodAdminMenuMessages.USERNAME_NOT_EXIST;
    }
}
