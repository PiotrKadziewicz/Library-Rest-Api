package com.library.service;

import com.library.domain.CopyBook;
import com.library.repository.CopyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CopyBookDbService {
    @Autowired
    private CopyBookRepository copyBookRepository;

    public Set<CopyBook> getAllCopyBooks() {
        return copyBookRepository.findAll();
    }

    public CopyBook saveCopyBook(CopyBook copyBook) {
        return copyBookRepository.save(copyBook);
    }

    public void deleteCopyBook(long id) {
        copyBookRepository.deleteById(id);
    }

    public Set<CopyBook> getAllCopyBookByIdAndStatus(Long id, String status) {
        return copyBookRepository.findAllByBookTitle_IdAndStatus(id, status);
    }

    public long countCopiesBook(String status, Long id) {
        return copyBookRepository.countByStatusAndBookTitle_Id(status, id);
    }
}
