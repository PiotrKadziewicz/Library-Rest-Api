package com.library.controller;

import com.library.domain.CopyBookDto;
import com.library.mapper.CopyBookMapper;
import com.library.service.CopyBookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class CopyBookController {
    @Autowired
    private CopyBookDbService service;

    @Autowired
    private CopyBookMapper copyBookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getAllCopyBooks")
    public List<CopyBookDto> getAllCopyBooks() {
        return copyBookMapper.mapToCopyBookDtoList(service.getAllCopyBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCopyBook")
    public CopyBookDto addCopyBook(@RequestBody CopyBookDto copyBookDto) {
        return copyBookMapper.mapToCopyBookDto(service.saveCopyBook(copyBookMapper.mapToCopyBook(copyBookDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getFreeCopies")
    public long getFreeCopies(@RequestParam(value = "status") String status, @RequestParam(value = "bookTitleId") long id) {
        return service.countCopiesBook(status, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopyBook")
    public void deleteCopyBook(@RequestParam Long id) {
        service.deleteCopyBook(id);
    }

}
