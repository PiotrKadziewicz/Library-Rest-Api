package com.library.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private String lastName;
    private LocalDate creationDate;
    private double account;
    private List<BorrowBook> borrowBooks = new ArrayList<>();
}
