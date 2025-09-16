package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket productBasket;

    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;
    }

    public void add(UUID id) {
        Product product = storageService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
        productBasket.addBasket(product.getId());
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItemList = productBasket.getBasket().entrySet().stream()
                .map(it -> new BasketItem(storageService.getProductById(it.getKey())
                        .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"))
                        , it.getValue()))
                .toList();
        return new UserBasket(basketItemList);
    }
}
