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

        //When
        dbService.saveUser(user);
        long id = user.getId();

        //Then
        Assert.assertEquals(1, dbService.getAllUsers().size());

        //CleanUp
        dbService.deleteUser(id);
    }
}
