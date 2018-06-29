package com.library.repository;

import com.library.domain.BookTitle;
import com.library.domain.CopyBook;
import com.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface CopyBookRepository extends CrudRepository<CopyBook, Long> {
    @Override
    Set<CopyBook> findAll();

    @Override
    CopyBook save(CopyBook copyBook);

    @Override
    void deleteById(Long id);

    Long countByStatusAndBookTitle_Id(String status, Long id);
    Set<CopyBook> findAllByBookTitle_IdAndStatus(Long id, String status);
    CopyBook findByBookTitle_Id(Long id);
}
