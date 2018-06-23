package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowBookDto {
    private long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private CopyBook copyBook;
    private User user;
}
