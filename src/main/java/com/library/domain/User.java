package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.scheduling.config.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "lastName")
    @NotNull
    private String lastName;

    @Column(name = "date")
    @NotNull
    private LocalDate creationDate;

    @Column(name = "account")
    @NotNull
    private double account;

    @OneToMany(
            targetEntity = BorrowBook.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<BorrowBook> borrowBooks = new ArrayList<>();

    public User(String name, String lastName, LocalDate creationDate, double account) {
        this.name = name;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.account = account;
    }

    public User(long id, String name, String lastName, LocalDate creationDate, double account) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.account = account;
    }
}
