package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOKS")
public class CopyBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "BOOK_TITLE_ID")
    private BookTitle bookTitle;

    @OneToMany(
            targetEntity = BorrowBook.class,
            fetch = FetchType.LAZY,
            mappedBy = "copyBook"
    )
    private List<BorrowBook> borrowBooks = new ArrayList<>();

    public CopyBook(String status, BookTitle bookTitle) {
        this.status = status;
        this.bookTitle = bookTitle;
    }

    public CopyBook(long id, String status, BookTitle bookTitle) {
        this.id = id;
        this.status = status;
        this.bookTitle = bookTitle;
    }
}
