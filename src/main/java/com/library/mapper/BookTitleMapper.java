package com.library.mapper;

import com.library.domain.BookTitle;
import com.library.domain.BookTitleDto;
import org.springframework.stereotype.Component;

@Component
public class BookTitleMapper {
    public BookTitle mapToBookTitle(final BookTitleDto bookTitleDto) {
        return new BookTitle(
                bookTitleDto.getId(),
                bookTitleDto.getTitle(),
                bookTitleDto.getAuthor(),
                bookTitleDto.getPublicationYear(),
                bookTitleDto.getCopyBooks()
        );
    }

    public BookTitleDto mapToBookTitleDto(final BookTitle bookTitle){
        return new BookTitleDto(
                bookTitle.getId(),
                bookTitle.getTitle(),
                bookTitle.getAuthor(),
                bookTitle.getPublicationYear(),
                bookTitle.getCopyBooks()
        );
    }
}
