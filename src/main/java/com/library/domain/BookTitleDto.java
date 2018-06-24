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
    private List<CopyBookDto> copyBooksDto = new ArrayList<>();
}
