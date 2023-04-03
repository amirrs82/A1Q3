package Controller;

import Model.Discount;
import Model.Food;
import Model.Restaurant;
import Model.Snappfood;
import Model.Users.Customer;
import Model.Users.RestaurantAdmin;
import View.Enums.Messages.CustomerMenuMessages;

import java.util.LinkedHashMap;

public class CustomerMenuController {
    public static CustomerMenuMessages checkChargeAccount(int amount) {
        if (amount > 0) {
            Customer currentUser = (Customer) Snappfood.getCurrentUser();
            int currentBalance = currentUser.getBalance();
            currentUser.setBalance(currentBalance + amount);
            return CustomerMenuMessages.SUCCESS;
        } else return CustomerMenuMessages.INVALID_AMOUNT;
    }
    //TODO:Duplicated code

    public static int getBalance() {
        return Snappfood.getCurrentUser().getBalance();
    }
    //TODO:Duplicated code

    public static CustomerMenuMessages checkShowMenu(String restaurantName, String category) {
        Restaurant restaurant = Snappfood.getRestaurantByName(restaurantName);
        if (restaurant != null)
            if (category != null)
                if (Controller.isValidCategory(category))
                    return CustomerMenuMessages.SUCCESS;
                else return CustomerMenuMessages.INVALID_CATEGORY;
            else return CustomerMenuMessages.SUCCESS;
        else return CustomerMenuMessages.RESTAURANT_NOT_EXIST;
    }

    public static CustomerMenuMessages addToCart(String restaurantName, String foodName, int number) {
        Restaurant restaurant = Snappfood.getRestaurantByName(restaurantName);
        Customer customer = (Customer) Snappfood.getCurrentUser();
        LinkedHashMap<Food, Integer> customerCart = customer.getCart();//TODO: dont get customerCart
        if (restaurant != null) {
            Food food = restaurant.getFoodByName(foodName);
            if (food != null)
                if (number > 0) {
                    if (customer.isInCart(food)) {
                        int currentAmount = customerCart.get(food);
                        customer.addFood(food, currentAmount + number);
                    } else
                        customer.addFood(food, number);
                    return CustomerMenuMessages.SUCCESS;
                } else return CustomerMenuMessages.INVALID_AMOUNT;
            else return CustomerMenuMessages.FOOD_NOT_EXIST;
        } else return CustomerMenuMessages.RESTAURANT_NOT_EXIST;
    }

    public static CustomerMenuMessages removeFromCart(String restaurantName, String foodName, int number) {
        Restaurant restaurant = Snappfood.getRestaurantByName(restaurantName);
        Food food = restaurant.getFoodByName(foodName);
        Customer customer = (Customer) Snappfood.getCurrentUser();
        LinkedHashMap<Food, Integer> customerCart = customer.getCart();
        if (customer.isInCart(food))
            if (number > 0)
                if (customer.hasEnoughFoodInCart(food, number)) {
                    int currentAmount = customerCart.get(food);
                    if (currentAmount == number) customer.removeFood(food);
                    else customerCart.put(food, currentAmount - number);
                    return CustomerMenuMessages.SUCCESS;
                } else return CustomerMenuMessages.NOT_ENOUGH_FOOD_IN_CART;
            else return CustomerMenuMessages.INVALID_AMOUNT;
        else return CustomerMenuMessages.NOT_IN_CART;
    }

    public static CustomerMenuMessages checkPurchaseCart(String discountCode) {
        Customer customer = (Customer) Snappfood.getCurrentUser();
        Discount discount;
        int discountValue = 0;
        if (discountCode != null) {
            discount = customer.getDiscount(discountCode);
            if (discount != null) {
                discountValue = discount.getAmount();
                customer.removeDiscount(discount);
                Snappfood.removeDiscount(discount);
            } else return CustomerMenuMessages.WRONG_DISCOUNT_CODE;
        }
        int netPrice = customer.getTotalPrice() - discountValue;
        if (customer.getBalance() >= netPrice) {
            for (Food food : customer.getCart().keySet()) {
                int foodNumber = customer.getCart().get(food);
                RestaurantAdmin restaurantAdmin = Snappfood.getRestaurantByName(food.getRestaurantName()).getOwner();
                int currentRestaurantBalance = restaurantAdmin.getBalance();
                restaurantAdmin.setBalance(currentRestaurantBalance + (food.getPrice() - food.getCost()) * foodNumber);
            }
            int customerCurrentBalance = customer.getBalance();
            customer.setBalance(customerCurrentBalance - netPrice);
            customer.getCart().clear();
            return CustomerMenuMessages.SUCCESS;
        } else return CustomerMenuMessages.NOT_ENOUGH_BALANCE;
    }
}
