package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.NoSuchProductException;
import org.skypro.skyshop.service.StorageService;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    StorageService storageService;

    @Mock
    ProductBasket productBasket;


    @InjectMocks
    BasketService basketService;

    //Добавление несуществующего товара в корзину приводит к выбросу исключения
    @Test
    public void givenNonExistentProduct_WhenAdding_ThenThrowNoSuchProductException() {
        UUID id1 = UUID.randomUUID();
        when(storageService.getProductById(id1)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.add(id1));
    }

    //Добавление существующего товара вызывает метод addProduct у мока ProductBasket
    @Test
    public void givenExistingProduct_WhenAdding_ThenCallAddBasket() {
        UUID id2 = UUID.randomUUID();
        Product product1 = new SimpleProduct(id2, "куртка", 3000);
        when(storageService.getProductById(id2)).thenReturn(Optional.of(product1));

        basketService.add(id2);


        verify(productBasket).addBasket(id2);
    }

    //Метод getUserBacket возвращает пустую корзину, если ProductBasket пуст
    @Test
    public void givenEmptyProductBasket_WhenGettingBasket_ThenReturnEmptyUserBasket() {
        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getTotal() == 0);
        assertTrue(result.getBasketItems().isEmpty());

    }

    //Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары
    @Test
    public void givenProductBasketWithItems_WhenGettingBasket_ThenReturnCorrectUserBasket() {
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();
        Product product3 = new SimpleProduct(id3, "мята", 300);
        Product product4 = new SimpleProduct(id4, "зерно", 500);

        Map<UUID, Integer> basketItems = new HashMap<>();
        basketItems.put(id3, 1);
        basketItems.put(id4, 2);


        when(productBasket.getBasket()).thenReturn(basketItems);
        when(storageService.getProductById(id3)).thenReturn(Optional.of(product3));
        when(storageService.getProductById(id4)).thenReturn(Optional.of(product4));

        UserBasket result = basketService.getUserBasket();

        assertFalse(result.getTotal() == 0);
        assertFalse(result.getBasketItems().isEmpty());
        assertEquals(1300, result.getTotal());
        assertEquals(2, result.getBasketItems().size());

    }
}