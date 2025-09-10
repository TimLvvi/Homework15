package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {

    String searchTerm();

    String contentType();
    @JsonIgnore
    default String getStringRepresentation() {
        return searchTerm() + " — " + contentType();
    }

    UUID getId();
}
