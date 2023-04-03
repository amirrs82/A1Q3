package View.Menus;

import Controller.SnappfoodAdminMenuController;
import View.Enums.Commands.SnappfoodAdminMenuCommands;
import View.Enums.Messages.SnappfoodAdminMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SnappfoodAdminMenu {

    public boolean run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine().trim();
            if (command.equals("logout")) return true;
            else if (SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SHOW_CURRENT_MENU) != null)
                System.out.println("Snappfood admin menu");
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.ADD_RESTAURANT)) != null)
                checkAddRestaurant(matcher);
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SHOW_RESTAURANT)) != null)
                showRestaurants(matcher);
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.REMOVE_RESTAURANT)) != null)
                checkRemoveRestaurant(matcher);
            else if ((matcher = SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SET_DISCOUNT)) != null)
                checkSetDiscount(matcher);
            else if (SnappfoodAdminMenuCommands.getMatcher(command, SnappfoodAdminMenuCommands.SHOW_DISCOUNTS) != null)
                showDiscounts();
            else System.out.println("invalid command!");
        }
    }

    private void checkAddRestaurant(Matcher matcher) {
        String name = matcher.group("name");
        String password = matcher.group("password");
        String type = matcher.group("type");
        SnappfoodAdminMenuMessages message = SnappfoodAdminMenuController.checkAddRestaurant(name, password, type);
        switch (message) {
            case INVALID_USERNAME_FORMAT:
                System.out.println("add restaurant failed: invalid username format");
                break;
            case USERNAME_EXIST:
                System.out.println("add restaurant failed: username already exists");
                break;
            case INVALID_PASSWORD_FORMAT:
                System.out.println("add restaurant failed: invalid password format");
                break;
            case WEAK_PASSWORD:
                System.out.println("add restaurant failed: weak password");
                break;
            case INVALID_TYPE_FORMAT:
                System.out.println("add restaurant failed: invalid type format");
                break;
            case SUCCESS:
                System.out.println("add restaurant successful");
                break;
        }
    }

    private void showRestaurants(Matcher matcher) {
        String type = matcher.group("type");
        System.out.print(SnappfoodAdminMenuController.showRestaurants(type));
    }

    private void checkRemoveRestaurant(Matcher matcher) {
        String name = matcher.group("name");
        SnappfoodAdminMenuMessages message = SnappfoodAdminMenuController.checkRemoveRestaurant(name);
        if (message == SnappfoodAdminMenuMessages.USERNAME_NOT_EXIST)
            System.out.println("remove restaurant failed: restaurant not found");
    }

    private void checkSetDiscount(Matcher matcher) {
        String username = matcher.group("username");
        int amount = Integer.parseInt(matcher.group("amount"));
        String code = matcher.group("code");
        SnappfoodAdminMenuMessages message = SnappfoodAdminMenuController.checkSetDiscount(username, amount, code);
        switch (message) {
            case USERNAME_NOT_EXIST:
                System.out.println("set discount failed: username not found");
                break;
            case INVALID_AMOUNT:
                System.out.println("set discount failed: invalid amount");
                break;
            case INVALID_CODE_FORMAT:
                System.out.println("set discount failed: invalid code format");
                break;
            case SUCCESS:
                System.out.println("set discount successful");
                break;
        }
    }

    private void showDiscounts() {
        System.out.print(SnappfoodAdminMenuController.showDiscounts());
    }
}
