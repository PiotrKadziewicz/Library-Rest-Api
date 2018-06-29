package com.library.service;

import com.library.domain.BorrowBook;
import com.library.domain.NotFoundException;
import com.library.repository.BorrowBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BorrowBookDbService {
    @Autowired
    private BorrowBookRepository borrowBookRepository;

    public Set<BorrowBook> getAllBorrowBooks() {
        return borrowBookRepository.findAll();
    }

    public BorrowBook saveBorrowBook(BorrowBook borrowBook) {
        return borrowBookRepository.save(borrowBook);
    }

    public BorrowBook getBorrowBookByCopyAndUserIds(Long bookId, Long userId) throws NotFoundException {
        return borrowBookRepository.findByCopyBook_IdAndUser_Id(bookId,userId).orElseThrow(NotFoundException::new);
    }
}
