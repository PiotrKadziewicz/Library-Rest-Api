package com.library.controller;

import com.library.domain.BookTitle;
import com.library.domain.BookTitleDto;
import com.library.domain.CopyBook;
import com.library.mapper.BookTitleMapper;
import com.library.service.BookTitleDbService;
import com.library.service.CopyBookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/library")
public class BookTitleController {


    @Autowired
    private BookTitleDbService service;

    @Autowired
    private BookTitleMapper bookTitleMapper;

    @Autowired
    private CopyBookDbService copyBookService;

    @RequestMapping(method = RequestMethod.GET, value = "getAllBooks")
    public Set<BookTitleDto> getAllBooks() {
        Set<BookTitleDto> bookTitleDtoList = bookTitleMapper.mapToBookTitleDtoList(service.getAllBookTitles());
        bookTitleDtoList.stream().forEach(b -> b.setCopies(copyBookService.countCopiesBook("Free", b.getId())));
        return bookTitleDtoList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookWithCopies")
    public BookTitleDto getBookWithCopies(@RequestParam Long bookTitleId) {
        BookTitleDto bookTitleDto = bookTitleMapper.mapToBookTitleDto(service.getBookTitle(bookTitleId).orElse(null));
        bookTitleDto.setCopies(copyBookService.countCopiesBook("Free", bookTitleId));
        return bookTitleDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookTitle")
    public BookTitleDto addBook(@RequestBody BookTitleDto bookTitleDto) {
        Optional<BookTitle> bookTitleOptional = service.getBookTitleByAuthorAndTitle(bookTitleDto.getAuthor(), bookTitleDto.getTitle());
        if (bookTitleOptional.isPresent()) {
            BookTitle bookTitle = bookTitleOptional.orElse(null);
            copyBookService.saveCopyBook(new CopyBook("Free", service.getBookTitle(bookTitle.getId()).orElse(null)));
            return bookTitleMapper.mapToBookTitleDto(bookTitle);
        } else {
            BookTitle bookTitle = service.saveBookTitle(bookTitleMapper.mapToBookTitle(bookTitleDto));
            copyBookService.saveCopyBook(new CopyBook("Free", bookTitle));
            return bookTitleMapper.mapToBookTitleDto(bookTitle);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBooTitle")
    public void deleteBook(@RequestParam Long id) {
        service.deleteBookTitle(id);
    }
}
