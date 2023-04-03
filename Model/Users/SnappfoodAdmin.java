package Model.Users;

import Model.Discount;
import Model.Restaurant;

public class SnappfoodAdmin extends User {
    private static SnappfoodAdmin snappfoodAdmin = null;

    private SnappfoodAdmin(String username, String password) {
        super(username, password);
    }

    public static SnappfoodAdmin getInstance(String username, String password) {
        if (snappfoodAdmin == null)
            snappfoodAdmin = new SnappfoodAdmin(username, password);
        return snappfoodAdmin;
    }

    @Override
    public String printDiscount(Discount discount) {
        return ") " + discount.getCode() + " | " + "amount=" + discount.getAmount() +
                " --> user=" + discount.getOwner().getUsername();
    }

    @Override
    public String printRestaurants(Restaurant restaurant) {
        return ") " + restaurant.getName() + ": type=" + restaurant.getType()
                + " balance=" + restaurant.getOwner().getBalance();
    }
}
