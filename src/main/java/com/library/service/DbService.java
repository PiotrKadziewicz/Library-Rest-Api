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

    //User
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    //BookTitle
    public List<BookTitle> getAllBookTitles() {
        return bookTitleRepository.findAll();
    }

    public Optional<BookTitle> getBookTitle(final Long id) {
        return bookTitleRepository.findById(id);
    }

    public void deleteBookTitle(long id) {
        bookTitleRepository.deleteById(id);
    }

    public BookTitle saveBookTitle(BookTitle bookTitle) {
        return bookTitleRepository.save(bookTitle);
    }

    //CopyBook
    public List<CopyBook> getAllCopyBooks() {
        return copyBookRepository.findAll();
    }

    public CopyBook saveCopyBook(CopyBook copyBook) {
        return copyBookRepository.save(copyBook);
    }

    public void deleteCopyBook(long id) {
        copyBookRepository.deleteById(id);
    }

    public CopyBook getCopyBook(Long id, String status){
        return copyBookRepository.findByIdAndStatus(id,status);
    }

    public long countCopiesBook(String status, Long id) {
        return copyBookRepository.countByStatusAndBookTitle_Id(status, id);
    }

    //BorrowBook
    public List<BorrowBook> getAllBorrowBooks() {
        return borrowBookRepository.findAll();
    }

    public BorrowBook saveBorrowBook(BorrowBook borrowBook) {
        return borrowBookRepository.save(borrowBook);
    }

    public void deleteBorrowBook(long id) {
        borrowBookRepository.deleteById(id);
    }
}
