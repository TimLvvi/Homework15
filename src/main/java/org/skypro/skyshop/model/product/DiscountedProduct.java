package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int percentageDiscount;
    private int finalPrice;

    public DiscountedProduct(UUID id, String name, int basePrice, int percentageDiscount) {
        super(id, name);
        if (basePrice < 1) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        this.basePrice = basePrice;
        if (percentageDiscount > 100 || percentageDiscount < 0) {
            throw new IllegalArgumentException("Процент должен быть числом в диапазоне от 0 до 100 включительно");
        }
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public int getPrice() {
        finalPrice = basePrice - basePrice * percentageDiscount * 100 / 10000;
        return finalPrice;
    }
    @JsonIgnore
    @Override
    public boolean isSpecial() {
        return true;
    }
    @JsonIgnore
    @Override
    public String toString() {
        return getName() + ": " + finalPrice
                + "(c учетом скидки " + percentageDiscount + "%)";
    }
}
