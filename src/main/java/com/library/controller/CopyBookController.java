package com.library.controller;

import com.library.domain.BookTitle;
import com.library.domain.CopyBookDto;
import com.library.mapper.CopyBookMapper;
import com.library.service.BookTitleDbService;
import com.library.service.CopyBookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/library")
public class CopyBookController {
    @Autowired
    private CopyBookDbService service;

    @Autowired
    private CopyBookMapper copyBookMapper;

    @Autowired
    private BookTitleDbService bookTitleDbService;

    @RequestMapping(method = RequestMethod.GET, value = "getAllCopyBooks")
    public Set<CopyBookDto> getAllCopyBooks() {
        return copyBookMapper.mapToCopyBookDtoList(service.getAllCopyBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopiesByStatusAndAuthorAndTitle")
    public long getFreeCopiesByAuthorAndTitle(@RequestParam(value = "status") String status, @RequestParam String author, String title) {
        Optional<BookTitle> bookTitleOptional = bookTitleDbService.getBookTitleByAuthorAndTitle(author, title);
        if (bookTitleOptional.isPresent()) {
            BookTitle bookTitle = bookTitleOptional.orElse(null);
            return service.countCopiesBook(status, bookTitle.getId());
        }
        return 0;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopiesByStatus")
    public long getFreeCopies(@RequestParam(value = "status") String status, @RequestParam(value = "bookTitleId") long id) {
        return service.countCopiesBook(status, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopyBook")
    public void deleteCopyBook(@RequestParam Long id) {
        service.deleteCopyBook(id);
    }

}
