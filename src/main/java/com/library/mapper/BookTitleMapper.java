package com.library.mapper;

import com.library.domain.BookTitle;
import com.library.domain.BookTitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookTitleMapper {

    public BookTitle mapToBookTitle(final BookTitleDto bookTitleDto) {
        return new BookTitle(
                bookTitleDto.getId(),
                bookTitleDto.getTitle(),
                bookTitleDto.getAuthor(),
                bookTitleDto.getPublicationYear()
        );
    }

    public BookTitleDto mapToBookTitleDto(final BookTitle bookTitle) {
        return new BookTitleDto(
                bookTitle.getId(),
                bookTitle.getTitle(),
                bookTitle.getAuthor(),
                bookTitle.getPublicationYear()
        );
    }

    public Set<BookTitleDto> mapToBookTitleDtoList(final Set<BookTitle> bookTitlesList) {
        return bookTitlesList.stream()
                .map(b -> new BookTitleDto(b.getId(), b.getTitle(), b.getAuthor(), b.getPublicationYear()))
                .collect(Collectors.toSet());
    }
}
