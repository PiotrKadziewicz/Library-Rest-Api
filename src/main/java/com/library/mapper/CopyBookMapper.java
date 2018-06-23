package com.library.mapper;

import com.library.domain.CopyBook;
import com.library.domain.CopyBookDto;
import org.springframework.stereotype.Component;

@Component
public class CopyBookMapper {
    public CopyBook mapToCopyBook(final CopyBookDto copyBookDto) {
        return new CopyBook(
                copyBookDto.getId(),
                copyBookDto.getStatus(),
                copyBookDto.getBookTitle(),
                copyBookDto.getBorrowBooks()
        );
    }

    public CopyBookDto mapToCopyBookDto(final CopyBook copyBook) {
        return new CopyBookDto(
                copyBook.getId(),
                copyBook.getStatus(),
                copyBook.getBookTitle(),
                copyBook.getBorrowBooks()
        );
    }
}
