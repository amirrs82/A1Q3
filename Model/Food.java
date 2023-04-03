package Model;

public class Food {
    private final String name;
    private final String category;
    private final String restaurantName;
    private final int price;
    private final int cost;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getCost() {
        return cost;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Food(String name, String category, String restaurantName, int price, int cost) {
        this.name = name;
        this.category = category;
        this.restaurantName = restaurantName;
        this.price = price;
        this.cost = cost;
    }
}
