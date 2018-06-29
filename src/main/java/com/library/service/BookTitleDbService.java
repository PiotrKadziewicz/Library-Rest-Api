package com.library.service;

import com.library.domain.BookTitle;
import com.library.repository.BookTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookTitleDbService {
    @Autowired
    private BookTitleRepository bookTitleRepository;

    public Set<BookTitle> getAllBookTitles() {
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

    public Optional<BookTitle> getBookTitleByAuthorAndTitle(String author, String title){
        return bookTitleRepository.findByAuthorAndTitle(author,title);
    }
}
