package org.skypro.skyshop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.SearchService;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.StorageService;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    //Поиск в случае отсутствия объектов в StorageService
    @Test
    public void givenEmptyStorage_WhenSearching_ThenReturnEmptyCollection() {
        when(storageService.searchableCollection()).thenReturn(Collections.emptySet());

        Collection<SearchResult> result = searchService.search("хлеб");

        assertTrue(result.isEmpty());
        assertNotNull(result);
    }

    //Поиск в случае, если объекты в StorageService есть, но нет подходящего
    @Test
    public void givenSearchablesWithNoMatches_WhenSearching_ThenReturnEmptyCollection() {
        Set<Searchable> searchables = new HashSet<>();
        Searchable product1 = new SimpleProduct(UUID.randomUUID(), "перчатки", 3435);
        Searchable article1 = new Article(UUID.randomUUID(), "какие джинсы выбрать", "джинсы лучше выбирать из соображений комфорта");
        searchables.add(product1);
        searchables.add(article1);
        when(storageService.searchableCollection()).thenReturn(searchables);

        Collection<SearchResult> results = searchService.search("туфли");

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    //Поиск, когда есть подходящий объект в StorageService
    @Test
    public void givenMatchingSearchable_WhenSearching_ThenReturnSearchResults() {
        Set<Searchable> searchables = new HashSet<>();
        Searchable product1 = new SimpleProduct(UUID.randomUUID(), "перчатки", 3435);
        Searchable article1 = new Article(UUID.randomUUID(), "какие джинсы выбрать", "джинсы лучше выбирать из соображений комфорта");
        Searchable article2 = new Article(UUID.randomUUID(), "перчатки зимой", "перчатки нужно брать теплые");
        searchables.add(product1);
        searchables.add(article1);
        searchables.add(article2);
        String searcWord = "перчатки";
        when(storageService.searchableCollection()).thenReturn(searchables);

        Collection<SearchResult> results = searchService.search(searcWord);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        assertTrue(results.stream()
                .allMatch(result -> result.getName().contains(searcWord)));
        assertFalse(results.stream()
                .allMatch(result -> result.getName().contains("сумка")));
    }
}