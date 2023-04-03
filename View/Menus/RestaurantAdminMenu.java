package View.Menus;

import Controller.RestaurantAdminMenuController;
import View.Enums.Commands.RestaurantAdminMenuCommands;
import View.Enums.Messages.RestaurantAdminMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RestaurantAdminMenu {
    public boolean run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine().trim();
            if (command.equals("logout")) return true;
            else if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.SHOW_CURRENT_MENU) != null)
                System.out.println("restaurant admin menu");
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.CHARGE_ACCOUNT)) != null)
                checkChargeAccount(matcher);
            else if (RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.SHOW_BALANCE) != null)
                showBalance();
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.ADD_FOOD)) != null)
                checkAddFood(matcher);
            else if ((matcher = RestaurantAdminMenuCommands.getMatcher(command, RestaurantAdminMenuCommands.REMOVE_FOOD)) != null)
                checkRemoveFood(matcher);
            else System.out.println("invalid command!");
        }
    }

    private void checkChargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        RestaurantAdminMenuMessages message = RestaurantAdminMenuController.checkChargeAccount(amount);
        switch (message) {
            case INVALID_AMOUNT:
                System.out.println("charge account failed: invalid cost or price");
                break;
            case SUCCESS:
                System.out.println("charge account successful");
                break;
        }
    }
    //TODO:Duplicated code
    private void showBalance() {
        int balance = RestaurantAdminMenuController.getBalance();
        System.out.println(balance);
    }

    private void checkAddFood(Matcher matcher) {
        String name = matcher.group("name");
        String category = matcher.group("category");
        int price = Integer.parseInt(matcher.group("price"));
        int cost = Integer.parseInt(matcher.group("cost"));
        RestaurantAdminMenuMessages message = RestaurantAdminMenuController.checkAddFood(name, category, price, cost);
        switch (message) {
            case INVALID_CATEGORY:
                System.out.println("add food failed: invalid category");
                break;
            case INVALID_NAME:
                System.out.println("add food failed: invalid food name");
                break;
            case FOOD_EXIST:
                System.out.println("add food failed: food already exists");
                break;
            case INVALID_AMOUNT:
                System.out.println("add food failed: invalid cost or price");
                break;
            case SUCCESS:
                System.out.println("add food successful");
                break;
        }
    }

    private void checkRemoveFood(Matcher matcher) {
        String name = matcher.group("name");
        RestaurantAdminMenuMessages message = RestaurantAdminMenuController.checkRemoveFood(name);
        if (message == RestaurantAdminMenuMessages.FOOD_NOT_EXIST)
            System.out.println("remove food failed: food not found");
    }
}
