package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookTitleDto {
    private long id;
    private String title;
    private String Author;
    private int publicationYear;
    private long copies;

    public BookTitleDto(long id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        Author = author;
        this.publicationYear = publicationYear;
    }
}
