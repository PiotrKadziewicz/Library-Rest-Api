package com.library.repository;

import com.library.domain.BookTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface BookTitleRepository extends CrudRepository<BookTitle, Long> {
    @Override
    Set<BookTitle> findAll();

    @Override
    Optional<BookTitle> findById(Long id);

    @Override
    BookTitle  save(BookTitle bookTitle);

    @Override
    void deleteById(Long aLong);

    Optional<BookTitle> findByAuthorAndTitle(String author, String title);
}