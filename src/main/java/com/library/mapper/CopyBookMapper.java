package com.library.mapper;

import com.library.domain.BorrowBook;
import com.library.domain.CopyBook;
import com.library.domain.CopyBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyBookMapper {
    @Autowired
    private BorrowBookMapper borrowBookMapper;

    @Autowired
    private BookTitleMapper bookTitleMapper;

    public CopyBook mapToCopyBook(final CopyBookDto copyBookDto) {
        return new CopyBook(
                copyBookDto.getId(),
                copyBookDto.getStatus(),
                bookTitleMapper.mapToBookTitle(copyBookDto.getBookTitleDto()),
                borrowBookMapper.mapToBorrowBookList(copyBookDto.getBorrowBooksDto())
        );
    }

    public CopyBookDto mapToCopyBookDto(final CopyBook copyBook) {
        return new CopyBookDto(
                copyBook.getId(),
                copyBook.getStatus(),
                bookTitleMapper.mapToBookTitleDto(copyBook.getBookTitle()),
                borrowBookMapper.mapToBorrowBookDtoList(copyBook.getBorrowBooks())
        );
    }

    public List<CopyBookDto> mapToCopyBookDtoList(final List<CopyBook> copyBookList) {
        return copyBookList.stream()
                .map(c -> new CopyBookDto(c.getId(), c.getStatus(), bookTitleMapper.mapToBookTitleDto(c.getBookTitle()), borrowBookMapper.mapToBorrowBookDtoList(c.getBorrowBooks())))
                .collect(Collectors.toList());
    }

    public List<CopyBook> mapToCopyBookList(final List<CopyBookDto> copyBookDtoList) {
        return copyBookDtoList.stream()
                .map(c -> new CopyBook(c.getId(), c.getStatus(), bookTitleMapper.mapToBookTitle(c.getBookTitleDto()), borrowBookMapper.mapToBorrowBookList(c.getBorrowBooksDto())))
                .collect(Collectors.toList());
    }
}
