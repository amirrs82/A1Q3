package Model.Users;

import Model.Discount;
import Model.Restaurant;
import Model.Snappfood;

public class User {
    private final String username;
    private String password;

    private int balance = 0;

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        Snappfood.addUser(this);
    }

    public String printDiscount(Discount discount) {
        return null;
    }

    public String printRestaurants(Restaurant restaurant) {
        return null;
    }
}
