package com.library.services;

import com.library.domain.*;
import com.library.mapper.BookTitleMapper;
import com.library.mapper.BorrowBookMapper;
import com.library.mapper.CopyBookMapper;
import com.library.mapper.UserMapper;
import com.library.service.BookTitleDbService;
import com.library.service.BorrowBookDbService;
import com.library.service.CopyBookDbService;
import com.library.service.UserDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {
    @Autowired
    private UserDbService userDbService;

    @Autowired
    private BookTitleDbService bookTitleDbService;

    @Autowired
    private CopyBookDbService copyBookDbService;

    @Autowired
    private BorrowBookDbService borrowBookDbService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookTitleMapper bookTitleMapper;

    @Autowired
    private CopyBookMapper copyBookMapper;

    @Autowired
    private BorrowBookMapper borrowBookMapper;


    @Test
    public void testSaveUser() {
        //Given
        User user = new User("Jan", "Kowalski", LocalDate.of(2015, 06, 04), 10);
        BookTitle bookTitle = new BookTitle("Title 1 ","Kajtek", 2011);
        CopyBook copyBook = new CopyBook("Free", bookTitle);
        BorrowBook borrowBook = new BorrowBook(LocalDate.of(2017,06,20),copyBook,user);

        //When
        userDbService.saveUser(user);
        bookTitleDbService.saveBookTitle(bookTitle);
        copyBookDbService.saveCopyBook(copyBook);
        borrowBookDbService.saveBorrowBook(borrowBook);

        long id = user.getId();
        long idBT = bookTitle.getId();
        long idCB = copyBook.getId();
        long idBB = borrowBook.getId();

        //Then

        Assert.assertEquals(1,userDbService.getAllUsers().size());
        Assert.assertEquals(1,bookTitleDbService.getAllBookTitles().size());
        Assert.assertEquals(1,copyBookDbService.getAllCopyBooks().size());
        Assert.assertEquals(1,borrowBookDbService.getAllBorrowBooks().size());
        Assert.assertEquals(1,copyBookDbService.countCopiesBook("Free",idBT));

        //CleanUp
        bookTitleDbService.deleteBookTitle(idBT);
        userDbService.deleteUser(id);
    }
    @Test
    public void addDate(){
        User user = new User("Piotr", "Nowak", LocalDate.of(2015, 06, 04), 10);
        BookTitle bookTitle = new BookTitle("Hobbit","J.R.R. Tolkien", 2011);
        CopyBook copyBook = new CopyBook("Borrowed", bookTitle);
        BorrowBook borrowBook = new BorrowBook(LocalDate.of(2017,06,20),copyBook,user);

        //When
        userDbService.saveUser(user);
        bookTitleDbService.saveBookTitle(bookTitle);
        copyBookDbService.saveCopyBook(copyBook);
        borrowBookDbService.saveBorrowBook(borrowBook);

        User user1 = new User("Jan", "Kowalski", LocalDate.of(2015, 06, 04), 10);
        BookTitle bookTitle1 = new BookTitle("ASP.NET CORE MVC 2","Adam Freeman", 2018);
        CopyBook copyBook1 = new CopyBook("Free", bookTitle1);

        //When
        userDbService.saveUser(user1);
        bookTitleDbService.saveBookTitle(bookTitle1);
        copyBookDbService.saveCopyBook(copyBook1);


        User user2 = new User("Grzegorz", "Brzęczyszczykiewicz", LocalDate.of(2015, 06, 04), 10);
        BookTitle bookTitle2 = new BookTitle("PORADNIK PRZETRWANIA W ŻYCIU","Bear Grylls", 2013);
        CopyBook copyBook2 = new CopyBook("Free", bookTitle2);

        //When
        userDbService.saveUser(user2);
        bookTitleDbService.saveBookTitle(bookTitle2);
        copyBookDbService.saveCopyBook(copyBook2);
    }
}
