package com.cg5labs.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg5labs.api.model.Bookmark;
import com.cg5labs.api.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }
    @GetMapping
    public List<Bookmark> getBookmarks(){
        return bookmarkService.getBookmarks();
    }

    @PostMapping
    public void createBookmark(@RequestBody Bookmark bookmark){
        bookmarkService.createBookmark(bookmark);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBookmark(@PathVariable("id") Long id){
        bookmarkService.deleteBookmark(id);
    }

    @PutMapping(path = "{id}")
    public void updateBookmark(@PathVariable("id") Long id, @RequestBody Bookmark bookmark){
        bookmarkService.updateBookmark(id, bookmark);
    }
}