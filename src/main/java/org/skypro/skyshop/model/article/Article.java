package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    public String articleTitle;
    public String articleText;
    private final UUID id;

    public Article(UUID id,String articleTitle, String articleText) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleText = articleText;
    }

    @Override
    public String toString() {
        return "Название статьи:" + articleTitle + ". " +
                "Текст статьи:" + articleText;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return articleTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleTitle);
    }

    @JsonIgnore
    @Override
    public String contentType() {
        return "ARTICLE";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
