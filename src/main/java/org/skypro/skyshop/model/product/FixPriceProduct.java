package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 500;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @JsonIgnore
    @Override
    public boolean isSpecial() {
        return true;
    }
    @JsonIgnore
    @Override
    public String toString() {
        return getName() + ": фиксированная цена " + FIX_PRICE;
    }
}
