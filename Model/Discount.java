package Model;

import Model.Users.Customer;

public class Discount {
    private final String code;
    private final int amount;
    private final Customer owner;
    public String getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }

    public Customer getOwner() {
        return owner;
    }

    public Discount(String code, int amount, Customer owner) {
        this.code = code;
        this.amount = amount;
        this.owner = owner;
        Snappfood.addDiscount(this);
    }
}
