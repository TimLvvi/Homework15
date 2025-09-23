package org.skypro.skyshop.model.search;

import org.skypro.skyshop.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {
    private StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    //метод должен принимать строку для поиска и возвращать коллекцию объектов SearchResult
    public Collection<SearchResult> search(String pattern) {
        return storageService.searchableCollection().stream()
                .filter(searchable -> searchable != null)
                .filter(searchable -> searchable.searchTerm().contains(pattern))
                .map(searchable -> SearchResult.fromSearchable(searchable))
                .collect(Collectors.toList());
    }
}
