package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        initialization();
    }

    //метод заполняющий мапы продуктами и статьями
    private void initialization() {
        Product product1 = new SimpleProduct(UUID.randomUUID(), "хлеб", 130);
        Product product2 = new DiscountedProduct(UUID.randomUUID(), "батон", 120, 40);
        Product product3 = new FixPriceProduct(UUID.randomUUID(), "масло");
        Product product4 = new DiscountedProduct(UUID.randomUUID(), "шоколад", 160, 50);
        Product product5 = new SimpleProduct(UUID.randomUUID(), "чипсы", 200);
        Product product6 = new SimpleProduct(UUID.randomUUID(), "жвачка", 40);
        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        productMap.put(product3.getId(), product3);
        productMap.put(product4.getId(), product4);
        productMap.put(product5.getId(), product5);
        productMap.put(product6.getId(), product6);


        Article article1 = new Article(UUID.randomUUID(), "что такое хлеб и почему хлеб вкусный", "хлеб — пищевой продукт, получаемый выпечкой разрыхлённого посредством дрожжей или закваски теста");
        Article article2 = new Article(UUID.randomUUID(), "польза шоколада", " шоколад изготавливается из какао-бобов, которые являются источником полезных веществ");
        Article article3 = new Article(UUID.randomUUID(), "чипсы и их состав", "чипсы могут изготавливать из тонких ломтиков картофеля или из сухого картофельного пюре в виде хлопьев, крупы или гранул");
        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
        articleMap.put(article3.getId(), article3);
    }
    //метод, который возвращает коллекцию Searchable путем объединения всех статей и всех продуктов в одну коллекцию внутри метода.
    public Set<Searchable> searchableCollection() {
        Set<Searchable> searchableCollection = new HashSet<>();
        searchableCollection.addAll(productMap.values());
        searchableCollection.addAll(articleMap.values());
        return searchableCollection;
    }

    //метод возвращающий коллекцию всех продуктов
    public Collection<Product> getProductCollection() {
        return productMap.values();
    }

    //метод возвращающий коллекцию всех статей
    public Collection<Article> getArticleCollection() {
        return articleMap.values();
    }


    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
