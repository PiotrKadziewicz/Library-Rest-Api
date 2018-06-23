package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="BORROW_BOOKS")
public class BorrowBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID")
    private long id;

    @Column(name = "BORROW_DATE")
    @NotNull
    private LocalDate borrowDate;

    @Column(name = "RETURN_DATE")
    @NotNull
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "COPY_BOOK_ID")
    private CopyBook copyBook;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
