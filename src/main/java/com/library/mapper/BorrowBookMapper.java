package com.library.mapper;

import com.library.domain.BorrowBook;
import com.library.domain.BorrowBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BorrowBookMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CopyBookMapper copyBookMapper;

    public BorrowBookDto mapToBorrowBookDto(final BorrowBook borrowBook) {
        return new BorrowBookDto(
                borrowBook.getId(),
                borrowBook.getBorrowDate(),
                borrowBook.getReturnDate(),
                copyBookMapper.mapToCopyBookDto(borrowBook.getCopyBook()),
                userMapper.mapToUserDto(borrowBook.getUser())
        );
    }

    public Set<BorrowBookDto> mapToBorrowBookDtoList(final Set<BorrowBook> borrowBookList) {
        return borrowBookList.stream()
                .map(b -> new BorrowBookDto(b.getId(), b.getBorrowDate(), b.getReturnDate(), copyBookMapper.mapToCopyBookDto(b.getCopyBook()), userMapper.mapToUserDto(b.getUser())))
                .collect(Collectors.toSet());
    }
}
