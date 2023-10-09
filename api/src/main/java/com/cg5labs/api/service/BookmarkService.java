package com.cg5labs.api.service;

import com.cg5labs.api.controller.BookmarkController;
import com.cg5labs.api.model.Bookmark;
import com.cg5labs.api.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.*;


@Service
public class BookmarkService {

    @Value("${com.cg5labs.api.bookmark.rules1}")
    private String rules1;

    //("#{${users}}")

    //@Value("#{${com.cg5labs.api.bookmark.rules}}")
    Logger logger = LoggerFactory.getLogger(BookmarkController.class);

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository =  bookmarkRepository;
    }

    public List<Bookmark> getBookmarks(){

        List<Bookmark> bookmarks = new ArrayList<>();

        bookmarkRepository.findAll().forEach(bookmarks::add);

        return bookmarks;
    }

    public void createBookmark(Bookmark bookmark) {

        logger.info("Inside createBookmark method...");

        logger.info(rules1);
        //logger.info(rules.getDescription());

        Optional<Bookmark> bookmarkOptional = bookmarkRepository.findBookmarkByUrl(bookmark.getUrl());

        logger.info("Check if bookmark already exists in the DB.");
        if(bookmarkOptional.isPresent()){
            throw new IllegalStateException("URL is already taken !!!");
        }
        logger.info("Check if bookmark verification checks pass.");
        if(verifyBookmark(bookmark)){
            // give the bookmark object a datestamp
            bookmark.setCreatedOn();
            bookmark.setCreatedBy("API");
            bookmarkRepository.save(bookmark);
        } else {
            throw new IllegalStateException("URL does not start with https://!!!");
        }
    }

    public boolean verifyBookmark(Bookmark bookmark) {

        String url  = bookmark.getUrl();

        // check if URL starts with http or https
        String regexStr = rules1;
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(url);

        //System.out.println(matcher.matches());
        return matcher.matches();

    }

    public void deleteBookmark(Long id) {
        boolean exist = bookmarkRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("bookmark id: " + id + " does not exist");
        }
        bookmarkRepository.deleteById(id);
    }

    public void updateBookmark(Long id, Bookmark bookmark) {
        Bookmark bookmarkExist = bookmarkRepository.findById(id).orElseThrow(() -> new IllegalStateException("bookmark id: " + id + " does not exist"));

        bookmarkExist.setUrl(bookmark.getUrl());
        bookmarkExist.setDescription(bookmark.getDescription());

        bookmarkRepository.save(bookmarkExist);
    }
}