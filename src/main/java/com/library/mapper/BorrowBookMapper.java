package com.library.mapper;

import com.library.domain.BorrowBook;
import com.library.domain.BorrowBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowBookMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CopyBookMapper copyBookMapper;

    public BorrowBook mapToBorrowBook(final BorrowBookDto borrowBookDto) {
        return new BorrowBook(
                borrowBookDto.getId(),
                borrowBookDto.getBorrowDate(),
                borrowBookDto.getReturnDate(),
                copyBookMapper.mapToCopyBook(borrowBookDto.getCopyBookDto()),
                userMapper.mapToUser(borrowBookDto.getUserDto())
        );
    }

    public BorrowBookDto mapToBorrowBookDto(final BorrowBook borrowBook) {
        return new BorrowBookDto(
                borrowBook.getId(),
                borrowBook.getBorrowDate(),
                borrowBook.getReturnDate(),
                copyBookMapper.mapToCopyBookDto(borrowBook.getCopyBook()),
                userMapper.mapToUserDto(borrowBook.getUser())
        );
    }

    public List<BorrowBookDto> mapToBorrowBookDtoList(final List<BorrowBook> borrowBookList) {
        return borrowBookList.stream()
                .map(b -> new BorrowBookDto(b.getId(), b.getBorrowDate(), b.getReturnDate(), copyBookMapper.mapToCopyBookDto(b.getCopyBook()), userMapper.mapToUserDto(b.getUser())))
                .collect(Collectors.toList());
    }

    public List<BorrowBook> mapToBorrowBookList(final List<BorrowBookDto> borrowBookDtoList) {
        return borrowBookDtoList.stream()
                .map(b -> new BorrowBook(b.getId(), b.getBorrowDate(), b.getReturnDate(), copyBookMapper.mapToCopyBook(b.getCopyBookDto()), userMapper.mapToUser(b.getUserDto())))
                .collect(Collectors.toList());
    }
}
