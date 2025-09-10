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

    public Set<SearchResult> search(String pattern) {
        Set<Searchable> resultsSearchable = storageService.searchableCollection().stream()
                .filter(searchable -> searchable != null)
                .filter(searchable -> searchable.searchTerm().contains(pattern))
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        Set<SearchResult> resultsSearch = new TreeSet<>();
        for (Searchable searchable : resultsSearchable) {
            resultsSearch.add(SearchResult.fromSearchable(searchable));
        }
        return resultsSearch;
    }
}
