package com.cg5labs.api.repository;

import com.cg5labs.api.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    // SELECT * FROM customerdb WHERE email = ?
    Optional<Bookmark> findBookmarkByDescription (String description);
    Optional<Bookmark> findBookmarkByUrl (String url);
    Optional<Bookmark> findBookmarkById (Long id);


}