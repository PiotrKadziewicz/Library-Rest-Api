package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOKS_TITLES")
public class BookTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    @NotNull
    private String title;

    @Column(name = "AUTHOR")
    @NotNull
    private String author;

    @Column(name = "PUBLICATION_YEAR")
    @NotNull
    private int publicationYear;

    @OneToMany(
            targetEntity = CopyBook.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "bookTitle"
    )
    private Set<BorrowBook> borrowBooks = new HashSet<>();

    public BookTitle(String title, String author, int publicationYear) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public BookTitle(long id, String title, String author, int publicationYear) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
    }

}
