package View.Menus;

import Controller.CustomerMenuController;
import Model.Discount;
import Model.Restaurant;
import Model.Snappfood;
import Model.Users.Customer;
import View.Enums.Commands.CustomerMenuCommands;
import View.Enums.Messages.CustomerMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CustomerMenu {
    private final Customer customer = (Customer) Snappfood.getCurrentUser();

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
        int balance = CustomerMenuController.getBalance();
        System.out.println(balance);
    }

    private void showRestaurants(Matcher matcher) {
        String type = matcher.group("type");
        int i = 1;
        if (type == null) for (Restaurant restaurant : Snappfood.getRestaurants())
            System.out.println((i++) + customer.printRestaurants(restaurant));
        else for (Restaurant restaurant : Snappfood.getRestaurants())
            if (restaurant.getType().equals(type)) System.out.println((i++) + customer.printRestaurants(restaurant));
    }

    //TODO:Duplicated code and use model in view

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
                showMenu(restaurantName, category);
                break;
        }
    }

    private void showMenu(String restaurantName, String category) {
        Restaurant restaurant = Snappfood.getRestaurantByName(restaurantName);
        String output;
        if (category == null) {
            output = restaurant.printFoods("starter");
            System.out.println("<< STARTER >>");
            if (!output.isEmpty()) System.out.print(output);

            output = restaurant.printFoods("entree");
            System.out.println("<< ENTREE >>");
            if (!output.isEmpty()) System.out.print(output);

            output = restaurant.printFoods("dessert");
            System.out.println("<< DESSERT >>");
            if (!output.isEmpty()) System.out.print(output);
        } else {
            output = restaurant.printFoods(category);
            if (!output.isEmpty())
                System.out.print(output);
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
        System.out.println(customer.printCart());
    }

    private void showDiscounts() {
        int i = 1;
        for (Discount discount : customer.getDiscounts())
            System.out.println((i++) + customer.printDiscount(discount));
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
