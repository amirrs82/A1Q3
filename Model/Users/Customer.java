package Model.Users;

import Model.Discount;
import Model.Food;
import Model.Restaurant;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Customer extends User {
    private final LinkedHashMap<Food, Integer> cart = new LinkedHashMap<>();
    ArrayList<Discount> discounts = new ArrayList<>();

    public void addFood(Food food, int amount) {
        cart.put(food, amount);
    }

    public void removeFood(Food food) {
        cart.remove(food);
    }

    public LinkedHashMap<Food, Integer> getCart() {
        return cart;
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public void removeDiscount(Discount discount) {
        discounts.removeIf(discount::equals);
    }

    public Discount getDiscount(String code) {
        for (Discount discount : discounts)
            if (discount.getCode().equals(code)) return discount;
        return null;
    }

    public boolean isInCart(Food food) {
        return cart.get(food) != null;
    }

    public boolean hasEnoughFoodInCart(Food food, int number) {
        return cart.get(food) >= number;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Food food : cart.keySet())
            totalPrice += food.getPrice() * cart.get(food);
        return totalPrice;
    }

    @Override
    public String printDiscount(Discount discount) {
        return ") " + discount.getCode() + " | " + "amount=" + discount.getAmount() + "\n";
    }

    @Override
    public String printRestaurants(Restaurant restaurant) {
        return ") " + restaurant.getName() + ": type=" + restaurant.getType() + "\n";
    }

    public String printCart(Food food) {
        return ") " + food.getName() + " | restaurant=" + food.getRestaurantName() +
                " price=" + food.getPrice() * cart.get(food) + "\n";
    }

    public Customer(String username, String password) {
        super(username, password);
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }
}
