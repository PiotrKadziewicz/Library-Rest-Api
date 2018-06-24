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
public class CopyBookDto {
    private long id;
    private String status;
    private BookTitleDto bookTitleDto;
    private List<BorrowBookDto> borrowBooksDto = new ArrayList<>();
}
