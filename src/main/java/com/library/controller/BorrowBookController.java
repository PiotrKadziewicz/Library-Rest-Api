package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BorrowBookMapper;
import com.library.repository.CopyBookRepository;
import com.library.service.BorrowBookDbService;
import com.library.service.CopyBookDbService;
import com.library.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class BorrowBookController {
    @Autowired
    private BorrowBookMapper borrowBookMapper;

    @Autowired
    private BorrowBookDbService service;

    @Autowired
    private CopyBookDbService copyBookService;

    @Autowired
    private UserDbService userService;

    //BorrowBook
    @RequestMapping(method = RequestMethod.GET, value = "getAllBorrowBooks")
    public List<BorrowBookDto> getAllBorrowBooks() {
        return borrowBookMapper.mapToBorrowBookDtoList(service.getAllBorrowBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook")
    public BorrowBookDto borrowBookDto(@RequestBody BorrowBookDto borrowBookDto) {
        User user = userService.getUser(borrowBookDto.getUserDto().getId()).orElse(null);
        return rentBook(borrowBookDto.getCopyBookDto().getBookTitleDto().getId(), user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBookByIds")
    public BorrowBookDto borrowBookDto(@RequestParam(value = "bookTitleId") Long bookId, @RequestParam(value = "userId") Long userId) {
        User user = userService.getUser(userId).orElse(null);
        if (user.getAccount() < 3) {
            return null;
        } else {
            return rentBook(bookId, user);
        }
    }

    private BorrowBookDto rentBook(Long id, User user) {
        List<CopyBook> freeBoks = copyBookService.getAllCopyBookByIdAndStatus(id, "Free");
        if (freeBoks.size() > 0) {
            CopyBook copyBook = freeBoks.get(0);
            copyBook.setStatus("Borrowed");
            copyBookService.saveCopyBook(copyBook);
            BorrowBook borrowBook = new BorrowBook(LocalDate.now(), copyBook, user);
            return borrowBookMapper.mapToBorrowBookDto(borrowBook);
        } else
            return null;
    }

    // Return book
//    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
//    public BorrowBookDto returnbook(@RequestParam Long borrowBookId) {
//        BorrowBook borrowBook = service.getBorrowBookById(borrowBookId);
//        borrowBook.setReturnDate(LocalDate.now());
//        borrowBook.getCopyBook().setStatus("Free");
//        service.saveBorrowBook(borrowBook);
//        return borrowBookMapper.mapToBorrowBookDto(borrowBook);
//    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBookByIds")
    public BorrowBookDto returnBookByIds(@RequestParam(value = "bookTitleId") Long bookId, @RequestParam(value = "userId") Long userId) throws NotFoundException {
        try {
            BorrowBook borrowBook = service.getBorrowBookByCopyAndUserIds(bookId, userId);
            borrowBook.setReturnDate(LocalDate.now());
            Period period = Period.between(borrowBook.getBorrowDate(), borrowBook.getReturnDate());
            if (period.getMonths() > 2) {
                User user = userService.getUser(userId).orElse(null);
                if (user != null) {
                    user.setAccount(user.getAccount() - 3.0);
                    userService.saveUser(user);
                }
            }
            borrowBook.getCopyBook().setStatus("Free");
            service.saveBorrowBook(borrowBook);
            return borrowBookMapper.mapToBorrowBookDto(borrowBook);
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }
}
