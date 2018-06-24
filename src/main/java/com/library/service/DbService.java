package com.library.service;

import com.library.domain.BookTitle;
import com.library.domain.BorrowBook;
import com.library.domain.CopyBook;
import com.library.domain.User;
import com.library.repository.BookTitleRepository;
import com.library.repository.BorrowBookRepository;
import com.library.repository.CopyBookRepository;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookTitleRepository bookTitleRepository;

    @Autowired
    private CopyBookRepository copyBookRepository;

    @Autowired
    private BorrowBookRepository borrowBookRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public List<BookTitle> getAllBookTitles() {
        return bookTitleRepository.findAll();
    }

    public Optional<BookTitle> getBookTitle(final Long id) {
        return bookTitleRepository.findById(id);
    }

    public BookTitle saveBookTitle(BookTitle bookTitle) {
        return bookTitleRepository.save(bookTitle);
    }

    public List<CopyBook> getAllCopyBooks() {
        return copyBookRepository.findAll();
    }

    public CopyBook saveCopyBook(CopyBook copyBook) {
        return copyBookRepository.save(copyBook);
    }

    public List<BorrowBook> getAllBorrowBooks() {
        return borrowBookRepository.findAll();
    }

    public BorrowBook saveBorrowBook(BorrowBook borrowBook) {
        return borrowBookRepository.save(borrowBook);
    }
}
