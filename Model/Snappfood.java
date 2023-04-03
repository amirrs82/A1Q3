package Model;

import Model.Users.User;

import java.util.ArrayList;

public class Snappfood {
    private static User currentUser;

    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Restaurant> restaurants = new ArrayList<>();
    private static final ArrayList<Discount> discounts = new ArrayList<>();


    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User removedUser) {
        users.removeIf(removedUser::equals);
    }

    public static void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public static void removeRestaurant(Restaurant removedRestaurant) {
        restaurants.removeIf(removedRestaurant::equals);
    }

    public static void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public static void removeDiscount(Discount removedDiscount) {
        discounts.removeIf(removedDiscount::equals);
    }//TODO:

    public static User getUserByUsername(String username) {
        for (User user : users)
            if (user.getUsername().equals(username)) return user;
        return null;
    }

    public static Restaurant getRestaurantByName(String name) {
        for (Restaurant restaurant : restaurants)
            if (restaurant.getName().equals(name)) return restaurant;
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Snappfood.currentUser = currentUser;
    }
}
