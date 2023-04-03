package Model;

import Model.Users.RestaurantAdmin;

import java.util.ArrayList;

public class Restaurant {
    private final ArrayList<Food> foods = new ArrayList<>();
    private final String name;
    private final String type;
    private String password;
    private final RestaurantAdmin owner;

    public boolean foodExist(String name) {
        for (Food food : foods)
            if (food.getName().equals(name)) return true;
        return false;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(String name) {
        foods.removeIf(food -> food.getName().equals(name));
    }

    public Food getFoodByName(String foodName) {
        for (Food food : foods)
            if (food.getName().equals(foodName)) return food;
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public RestaurantAdmin getOwner() {
        return owner;
    }

    public String printFoods(String category) {
        StringBuilder output = new StringBuilder();
        for (Food food : foods)
            if (food.getCategory().equals(category))
                output.append(food.getName()).append(" | ").append("price=").append(food.getPrice()).append("\n");
        return output.toString();
    }

    public Restaurant(String name, String type, RestaurantAdmin owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
        Snappfood.addRestaurant(this);
    }
}
