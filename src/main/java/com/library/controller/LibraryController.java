package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BookTitleMapper;
import com.library.mapper.BorrowBookMapper;
import com.library.mapper.CopyBookMapper;
import com.library.mapper.UserMapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    private DbService service;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CopyBookMapper copyBookMapper;

    @Autowired
    private BookTitleMapper bookTitleMapper;

    @Autowired
    private BorrowBookMapper borrowBookMapper;

    //User
    @RequestMapping(method = RequestMethod.GET, value = "getAllUser")
    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(service.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long id) {
        return userMapper.mapToUserDto(service.getUser(id).orElse(null));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addUser")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(service.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
    }

    //BookTitle
    @RequestMapping(method = RequestMethod.GET, value = "getAllBooks")
    public List<BookTitleDto> getAllBooks() {
        return bookTitleMapper.mapToBookTitleDtoList(service.getAllBookTitles());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookTitile")
    public BookTitleDto addBook(@RequestBody BookTitleDto bookTitleDto) {
        return bookTitleMapper.mapToBookTitleDto(service.saveBookTitle(bookTitleMapper.mapToBookTitle(bookTitleDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long id) {
        service.deleteBookTitle(id);
    }

    //CopyBook

    @RequestMapping(method = RequestMethod.GET, value = "getAllCopyBook")
    public List<CopyBookDto> getAllCopyBooks() {
        return copyBookMapper.mapToCopyBookDtoList(service.getAllCopyBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCopyBook")
    public CopyBookDto addCopyBook(@RequestBody CopyBookDto copyBookDto) {
        return copyBookMapper.mapToCopyBookDto(service.saveCopyBook(copyBookMapper.mapToCopyBook(copyBookDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getFreeCopies")
    public long getFreeCopies(@RequestParam(value = "status") String status, @RequestParam(value = "bookTitleId") long id) {
        return service.countCopiesBook(status, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteCopyBook(@RequestParam Long id) {
        service.deleteCopyBook(id);
    }


    //BorrowBook
    @RequestMapping(method = RequestMethod.POST, value = "borrowBook")
    public BorrowBookDto borrowBookDto(@RequestBody BorrowBookDto borrowBookDto) {
        CopyBook copyBook = service.getCopyBook(borrowBookDto.getCopyBookDto().getId(),"Free");
        User user = service.getUser(borrowBookDto.getUserDto().getId()).orElse(null);
        long freeBoks = service.countCopiesBook("Free", copyBook.getBookTitle().getId());

        if (freeBoks != 0) {
            copyBook.setStatus("Borrowed");
            service.saveCopyBook(copyBook);
            BorrowBook borrowBook = new BorrowBook(LocalDate.now(),copyBook,user);
            return borrowBookMapper.mapToBorrowBookDto(borrowBook);
        } else
            return null;
    }
}

