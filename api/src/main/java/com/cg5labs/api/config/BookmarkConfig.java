package com.cg5labs.api.config;

import com.cg5labs.api.model.Bookmark;
import com.cg5labs.api.repository.BookmarkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookmarkConfig {

    @Bean
    CommandLineRunner commandLineRunnerBookmark(BookmarkRepository bookmarkRepository){
        return args -> {
            Bookmark google = new Bookmark(
                    "https://google.com", "Search google.com"
            );
            Bookmark yahoo = new Bookmark(
                    "https://yahoo.com",
                    "Search yahoo.com"
            );

            bookmarkRepository.saveAll(List.of(google, yahoo));
        };
    }
}