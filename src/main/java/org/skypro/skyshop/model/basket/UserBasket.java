package org.skypro.skyshop.model.basket;

import java.util.ArrayList;
import java.util.List;

public class UserBasket {
    private List<BasketItem> basketItems;
    private int total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = basketItems.stream()
                .mapToInt(sumOneTypeProduct -> sumOneTypeProduct.getQuantity() * sumOneTypeProduct.getProduct().getPrice())
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return total;
    }
}
