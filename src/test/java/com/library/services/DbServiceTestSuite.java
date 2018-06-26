package com.library.services;

import com.library.domain.*;
import com.library.mapper.BookTitleMapper;
import com.library.mapper.BorrowBookMapper;
import com.library.mapper.CopyBookMapper;
import com.library.mapper.UserMapper;
import com.library.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {
    @Autowired
    private DbService dbService;

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
        User user = new User("Kajetan", "Bimbała", LocalDate.of(2018, 06, 04), 10);
        BookTitle bookTitle = new BookTitle("Book1","Author 1", 2010);
        CopyBook copyBook = new CopyBook("Free", bookTitle);
        BorrowBook borrowBook = new BorrowBook(LocalDate.of(2018,06,20),LocalDate.of(2018,06,25),copyBook,user);

        //When
        dbService.saveUser(user);
        dbService.saveBookTitle(bookTitle);
        dbService.saveCopyBook(copyBook);
        dbService.saveBorrowBook(borrowBook);

        long id = user.getId();
        long idBT = bookTitle.getId();
        long idCB = copyBook.getId();
        long idBB = borrowBook.getId();

        //Then

        Assert.assertEquals(1,dbService.getAllUsers().size());
        Assert.assertEquals(1,dbService.getAllBookTitles().size());
        Assert.assertEquals(1,dbService.getAllCopyBooks().size());
        Assert.assertEquals(1,dbService.getAllBorrowBooks().size());
        Assert.assertEquals(1,dbService.countCopiesBook("Free",idBT));

        //CleanUp
//        dbService.deleteBorrowBook(idBB);
//        dbService.deleteCopyBook(idCB);
//        dbService.deleteBookTitle(idBT);
//        dbService.deleteUser(id);
    }
}
