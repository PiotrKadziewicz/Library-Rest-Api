package com.library.controller;

import com.library.domain.*;
import com.library.mapper.BorrowBookMapper;
import com.library.repository.CopyBookRepository;
import com.library.service.BookTitleDbService;
import com.library.service.BorrowBookDbService;
import com.library.service.CopyBookDbService;
import com.library.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private BookTitleDbService bookTitleDbService;

    //BorrowBook
    @RequestMapping(method = RequestMethod.GET, value = "getAllBorrowBooks")
    public List<BorrowBookDto> getAllBorrowBooks() {
        return borrowBookMapper.mapToBorrowBookDtoList(service.getAllBorrowBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook")
    public BorrowBookDto borrowBookDto(@RequestParam String author, @RequestParam String title, @RequestParam(value = "userId") Long userId) throws UserAccountException {
        if (bookTitleDbService.getBookTitleByAuthorAndTitle(author, title).isPresent()) {
            BookTitle bookTitle = bookTitleDbService.getBookTitleByAuthorAndTitle(author, title).orElse(null);
            List<CopyBook> freeBooks = copyBookService.getAllCopyBookByIdAndStatus(bookTitle.getId(), "Free");
            User user = userService.getUser(userId).orElse(null);
            if (!freeBooks.isEmpty() && user.getAccount() >= 3) {
                CopyBook copyBook = freeBooks.get(0);
                copyBook.setStatus("Borrowed");
                copyBookService.saveCopyBook(copyBook);
                BorrowBook borrowBook = new BorrowBook(LocalDate.now(), copyBook, user);
                service.saveBorrowBook(borrowBook);
                return borrowBookMapper.mapToBorrowBookDto(borrowBook);
            }
        }
        throw new UserAccountException("No book or not enought money in your account");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBookByIds")
    public BorrowBookDto returnBookByIds(@RequestParam(value = "copyBookId") Long bookId, @RequestParam(value = "userId") Long userId) throws NotFoundException {
        BorrowBook borrowBook = service.getBorrowBookByCopyAndUserIds(bookId, userId);
        borrowBook.setReturnDate(LocalDate.now());
        long monthsBetween = ChronoUnit.MONTHS.between(borrowBook.getBorrowDate(), borrowBook.getReturnDate());
        if (monthsBetween > 2) {
            Optional<User> userOptional = userService.getUser(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.orElse(null);
                user.setAccount(user.getAccount() - 3.0);
                userService.saveUser(user);
            }
        }
        borrowBook.getCopyBook().setStatus("Free");
        service.saveBorrowBook(borrowBook);
        return borrowBookMapper.mapToBorrowBookDto(borrowBook);
    }
}
