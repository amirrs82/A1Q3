package Controller;


import Model.Snappfood;
import Model.Users.Customer;
import Model.Users.RestaurantAdmin;
import Model.Users.SnappfoodAdmin;
import Model.Users.User;
import View.Enums.Messages.LoginMenuMessages;

public class LoginMenuController {
    public static void createSnappfoodAdmin(String username, String password) {
        SnappfoodAdmin snappfoodAdmin = SnappfoodAdmin.getInstance(username, password);
        Snappfood.addUser(snappfoodAdmin);
    }

    public static LoginMenuMessages checkRegister(String username, String password) {
        User user = Snappfood.getUserByUsername(username);
        if (Controller.isValidUsernameFormat(username))
            if (user == null)
                if (Controller.isValidPasswordFormat(password))
                    if (Controller.isStrongPassword(password)) {
                        new Customer(username, password);
                        return LoginMenuMessages.SUCCESS;
                    } else return LoginMenuMessages.WEAK_PASSWORD;
                else return LoginMenuMessages.INVALID_PASSWORD_FORMAT;
            else return LoginMenuMessages.USERNAME_EXIST;
        else return LoginMenuMessages.INVALID_USERNAME_FORMAT;
    }

    public static LoginMenuMessages checkLogin(String username, String password) {
        User user = Snappfood.getUserByUsername(username);
        if (user != null)
            if (user.isPasswordCorrect(password)) {
                Snappfood.setCurrentUser(user);
                return LoginMenuMessages.SUCCESS;
            } else return LoginMenuMessages.WRONG_PASSWORD;
        else return LoginMenuMessages.USERNAME_NOT_EXIST;
    }

    public static LoginMenuMessages checkChangePassword(String username, String oldPassword, String newPassword) {
        User user = Snappfood.getUserByUsername(username);
        if (user != null) {
            if (user.isPasswordCorrect(oldPassword))
                if (Controller.isValidPasswordFormat(newPassword))
                    if (Controller.isStrongPassword(newPassword)) {
                        user.setPassword(newPassword);
                        return LoginMenuMessages.SUCCESS;
                    } else return LoginMenuMessages.WEAK_PASSWORD;
                else return LoginMenuMessages.INVALID_PASSWORD_FORMAT;
            else return LoginMenuMessages.WRONG_PASSWORD;
        } else return LoginMenuMessages.USERNAME_NOT_EXIST;
    }

    public static LoginMenuMessages checkRemoveAccount(String username, String password) {
        User user = Snappfood.getUserByUsername(username);
        if (user != null)
            if (user.isPasswordCorrect(password)) {
                Snappfood.getUsers().remove(user);
                if (user instanceof RestaurantAdmin)
                    Snappfood.removeRestaurant(Snappfood.getRestaurantByName(username));
                return LoginMenuMessages.SUCCESS;
            } else return LoginMenuMessages.WRONG_PASSWORD;
        else return LoginMenuMessages.USERNAME_NOT_EXIST;
    }
}
