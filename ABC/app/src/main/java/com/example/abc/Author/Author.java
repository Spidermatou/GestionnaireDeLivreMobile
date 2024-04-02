package com.example.abc.Author;

import androidx.annotation.NonNull;

public class Author {
    private String authorName;
    private Integer authorId;

    public Author(Integer authorId, String authorName) {
        this.authorName = authorName;
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    @NonNull
    @Override
    public String toString() {
        return authorName;
    }
}
