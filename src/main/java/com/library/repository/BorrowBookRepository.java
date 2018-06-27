package com.library.repository;

import com.library.domain.BorrowBook;
import lombok.Builder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepository extends CrudRepository<BorrowBook,Long> {
    @Override
    List<BorrowBook> findAll();

    @Override
    BorrowBook save(BorrowBook borrowBook);

    @Override
    void deleteById(Long id);

    @Override
    Optional<BorrowBook> findById(Long id);

    Optional<BorrowBook> findByCopyBook_IdAndUser_Id(Long bookId, Long userId);
}
