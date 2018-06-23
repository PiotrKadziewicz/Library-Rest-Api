package com.library.mapper;

import com.library.domain.BorrowBook;
import com.library.domain.BorrowBookDto;

public class BorrowBookMapper {
    public BorrowBook mapToBorrowBook(final BorrowBookDto borrowBookDto) {
        return new BorrowBook(
                borrowBookDto.getId(),
                borrowBookDto.getBorrowDate(),
                borrowBookDto.getReturnDate(),
                borrowBookDto.getCopyBook(),
                borrowBookDto.getUser()
        );
    }

    public BorrowBookDto mapToBorrowBookDto(final BorrowBook borrowBook) {
        return new BorrowBookDto(
                borrowBook.getId(),
                borrowBook.getBorrowDate(),
                borrowBook.getReturnDate(),
                borrowBook.getCopyBook(),
                borrowBook.getUser()
        );
    }
}
