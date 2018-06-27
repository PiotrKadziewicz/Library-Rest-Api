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
    public List<BookTitleDto> getAllBooks() {
        List<BookTitleDto> bookTitleDtoList = bookTitleMapper.mapToBookTitleDtoList(service.getAllBookTitles());
        bookTitleDtoList.stream().forEach(b -> b.setCopies(countBookCopies("Free", b.getId())));
        return bookTitleDtoList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookWithCopies")
    public BookTitleDto getBookWithCopies(@RequestParam Long bookTitleId){
        BookTitleDto bookTitleDto= bookTitleMapper.mapToBookTitleDto(service.getBookTitle(bookTitleId).orElse(null));
        bookTitleDto.setCopies(countBookCopies("Free",bookTitleId));
        return bookTitleDto;
    }

    private long countBookCopies(String status, Long id){
        return copyBookService.countCopiesBook(status,id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookTitle")
    public BookTitleDto addBook(@RequestBody BookTitleDto bookTitleDto) {
        if (service.getBookTitleByAuthorAndId(bookTitleDto.getAuthor(), bookTitleDto.getTitle())) {

            copyBookService.saveCopyBook(new CopyBook("Free", service.getBookTitle(bookTitleDto.getId()).orElse(null)));
            return bookTitleDto;
        }else{
            BookTitleDto bookTitleDto1 = bookTitleMapper.mapToBookTitleDto(service.saveBookTitle(bookTitleMapper.mapToBookTitle(bookTitleDto)));
            copyBookService.saveCopyBook(new CopyBook("Free", bookTitleMapper.mapToBookTitle(bookTitleDto1)));
            return bookTitleDto;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBooTitle")
    public void deleteBook(@RequestParam Long id) {
        service.deleteBookTitle(id);
    }
}
