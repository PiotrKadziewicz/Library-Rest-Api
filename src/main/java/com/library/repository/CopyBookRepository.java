package com.library.repository;

import com.library.domain.CopyBook;
import com.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CopyBookRepository extends CrudRepository<CopyBook,Long> {
    @Override
    List<CopyBook> findAll();

    @Override
    CopyBook  save(CopyBook copyBook);


}
