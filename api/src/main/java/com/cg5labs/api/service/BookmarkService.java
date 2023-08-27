package com.cg5labs.api.service;

import com.cg5labs.api.model.Bookmark;
import com.cg5labs.api.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.*;

@Service
public class BookmarkService {

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

        Optional<Bookmark> bookmarkOptional = bookmarkRepository.findBookmarkByUrl(bookmark.getUrl());

        if(bookmarkOptional.isPresent()){
            throw new IllegalStateException("URL is already taken !!!");
        }
        if(verifyBookmark(bookmark)){
            bookmarkRepository.save(bookmark);
        } else {
            throw new IllegalStateException("URL does not start with https://!!!");
        }
    }

    public boolean verifyBookmark(Bookmark bookmark) {

        String url  = bookmark.getUrl();

        // check if URL starts with http or https
        String regexStr = "http.*";
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