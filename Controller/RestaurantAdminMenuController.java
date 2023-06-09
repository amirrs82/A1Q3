package Controller;

import Model.Food;
import Model.Restaurant;
import Model.Snappfood;
import Model.Users.RestaurantAdmin;
import View.Enums.Messages.RestaurantAdminMenuMessages;

public class RestaurantAdminMenuController {
    public static RestaurantAdminMenuMessages checkChargeAccount(int amount) {
        if (amount > 0) {
            RestaurantAdmin restaurantAdmin = (RestaurantAdmin) Snappfood.getCurrentUser();
            int currentBalance = restaurantAdmin.getBalance();
            restaurantAdmin.setBalance(currentBalance + amount);
            return RestaurantAdminMenuMessages.SUCCESS;
        } else return RestaurantAdminMenuMessages.INVALID_AMOUNT;
    }
    //TODO: Duplicated code

    public static RestaurantAdminMenuMessages checkAddFood(String name, String category, int price, int cost) {
        RestaurantAdmin currentUser = (RestaurantAdmin) Snappfood.getCurrentUser();
        Restaurant restaurant = Snappfood.getRestaurantByName(currentUser.getUsername());
        if (Controller.isValidCategory(category))
            if (Controller.isValidFoodName(name)) if (!restaurant.foodExist(name)) if (price > 0 && cost > 0) {
                restaurant.addFood(new Food(name, category, restaurant.getName(), price, cost));
                return RestaurantAdminMenuMessages.SUCCESS;
            } else return RestaurantAdminMenuMessages.INVALID_AMOUNT;
            else return RestaurantAdminMenuMessages.FOOD_EXIST;
            else return RestaurantAdminMenuMessages.INVALID_NAME;
        else return RestaurantAdminMenuMessages.INVALID_CATEGORY;
    }

    public static RestaurantAdminMenuMessages checkRemoveFood(String name) {
        RestaurantAdmin currentUser = (RestaurantAdmin) Snappfood.getCurrentUser();
        Restaurant restaurant = Snappfood.getRestaurantByName(currentUser.getUsername());
        if (restaurant.foodExist(name)) {
            restaurant.removeFood(name);
            return RestaurantAdminMenuMessages.SUCCESS;
        } else return RestaurantAdminMenuMessages.FOOD_NOT_EXIST;
    }
}
