package com.library.repository;

import com.library.domain.BorrowBook;
import lombok.Builder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowBookRepository extends CrudRepository<BorrowBook,Long> {
    @Override
    List<BorrowBook> findAll();

    @Override
    BorrowBook save(BorrowBook borrowBook);
}
