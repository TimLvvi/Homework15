package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.SearchService;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {

    private final SearchService searchService;
    private final StorageService storageService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    //возвращаем коллекцию всех продуктов
    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductCollection();
    }

    //возвращаем коллекцию всех статей
    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticleCollection();
    }

    //Метод принимает принимать аргумент pattern и возращает коллекцию объектов SearchResult
    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.add(id);
        return "продкут добавлен ";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}
