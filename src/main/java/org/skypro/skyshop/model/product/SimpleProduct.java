package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price < 1) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @JsonIgnore
    @Override
    public boolean isSpecial() {
        return false;
    }
    @JsonIgnore
    @Override
    public String toString() {
        return getName() + ": " + price;
    }
}
