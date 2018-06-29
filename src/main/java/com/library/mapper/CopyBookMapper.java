package com.library.mapper;

import com.library.domain.BorrowBook;
import com.library.domain.CopyBook;
import com.library.domain.CopyBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CopyBookMapper {

    @Autowired
    private BookTitleMapper bookTitleMapper;

    public CopyBookDto mapToCopyBookDto(final CopyBook copyBook) {
        return new CopyBookDto(
                copyBook.getId(),
                copyBook.getStatus(),
                bookTitleMapper.mapToBookTitleDto(copyBook.getBookTitle())
        );
    }

    public Set<CopyBookDto> mapToCopyBookDtoList(final Set<CopyBook> copyBookList) {
        return copyBookList.stream()
                .map(c -> new CopyBookDto(c.getId(), c.getStatus(), bookTitleMapper.mapToBookTitleDto(c.getBookTitle())))
                .collect(Collectors.toSet());
    }
}
