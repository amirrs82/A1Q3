package View.Menus;

import Controller.Controller;
import Controller.CustomerMenuController;
import View.Enums.Commands.CustomerMenuCommands;
import View.Enums.Messages.CustomerMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {
    public boolean run(Scanner scanner) {
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine().trim();
            if (command.equals("logout"))
                return true;
            else if (CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_CURRENT_MENU) != null)
                System.out.println("customer menu");
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.CHARGE_ACCOUNT)) != null)
                checkChargeAccount(matcher);
            else if (CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_BALANCE) != null) showBalance();
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_RESTAURANT)) != null)
                showRestaurants(matcher);
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_MENU)) != null)
                showMenu(matcher);
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.ADD_TO_CART)) != null)
                checkAddToCart(matcher);
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.REMOVE_FROM_CART)) != null)
                checkRemoveFromCart(matcher);
            else if (CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_CART) != null)
                showCart();
            else if (CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.SHOW_DISCOUNTS) != null)
                showDiscounts();
            else if ((matcher = CustomerMenuCommands.getMatcher(command, CustomerMenuCommands.PURCHASE_CART)) != null)
                checkPurchaseCart(matcher);
            else System.out.println("invalid command!");
        }
    }

    private void checkChargeAccount(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("amount"));
        CustomerMenuMessages message = CustomerMenuController.checkChargeAccount(amount);
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
        int balance = Controller.getBalance();
        System.out.println(balance);
    }

    private void showRestaurants(Matcher matcher) {
        String type = matcher.group("type");
        System.out.print(CustomerMenuController.showRestaurants(type));
    }

    private void showMenu(Matcher matcher) {
        String restaurantName = matcher.group("restaurantName");
        String category = matcher.group("category");
        CustomerMenuMessages message = CustomerMenuController.checkShowMenu(restaurantName, category);
        switch (message) {
            case RESTAURANT_NOT_EXIST:
                System.out.println("show menu failed: restaurant not found");
                break;
            case INVALID_CATEGORY:
                System.out.println("show menu failed: invalid category");
                break;
            case SUCCESS:
                System.out.print(CustomerMenuController.showMenu(restaurantName, category));
                break;
        }
    }

    private void checkAddToCart(Matcher matcher) {
        String restaurantName = matcher.group("restaurantName");
        String foodName = matcher.group("foodName");
        String stringNumber = matcher.group("number");
        CustomerMenuMessages message;
        if (stringNumber != null) {
            int number = Integer.parseInt(stringNumber);
            message = CustomerMenuController.addToCart(restaurantName, foodName, number);
        } else message = CustomerMenuController.addToCart(restaurantName, foodName, 1);

        switch (message) {
            case RESTAURANT_NOT_EXIST:
                System.out.println("add to cart failed: restaurant not found");
                break;
            case FOOD_NOT_EXIST:
                System.out.println("add to cart failed: food not found");
                break;
            case INVALID_AMOUNT:
                System.out.println("add to cart failed: invalid number");
                break;
            case SUCCESS:
                System.out.println("add to cart successful");
                break;
        }
    }

    private void checkRemoveFromCart(Matcher matcher) {
        String restaurantName = matcher.group("restaurantName");
        String foodName = matcher.group("foodName");
        String stringNumber = matcher.group("number");
        CustomerMenuMessages message;
        if (stringNumber != null) {
            int number = Integer.parseInt(stringNumber);
            message = CustomerMenuController.removeFromCart(restaurantName, foodName, number);
        } else message = CustomerMenuController.removeFromCart(restaurantName, foodName, 1);
        switch (message) {
            case NOT_IN_CART:
                System.out.println("remove from cart failed: not in cart");
                break;
            case INVALID_AMOUNT:
                System.out.println("remove from cart failed: invalid number");
                break;
            case NOT_ENOUGH_FOOD_IN_CART:
                System.out.println("remove from cart failed: not enough food in cart");
                break;
            case SUCCESS:
                System.out.println("remove from cart successful");
                break;
        }
    }

    private void showCart() {
        System.out.println(CustomerMenuController.showCart());
    }

    private void showDiscounts() {
        System.out.print(CustomerMenuController.showDiscounts());
    }

    private void checkPurchaseCart(Matcher matcher) {
        String discountCode = matcher.group("discountCode");
        CustomerMenuMessages message = CustomerMenuController.checkPurchaseCart(discountCode);
        switch (message) {
            case WRONG_DISCOUNT_CODE:
                System.out.println("purchase failed: invalid discount code");
                break;
            case NOT_ENOUGH_BALANCE:
                System.out.println("purchase failed: inadequate money");
                break;
            case SUCCESS:
                System.out.println("purchase successful");
                break;
        }
    }
}
