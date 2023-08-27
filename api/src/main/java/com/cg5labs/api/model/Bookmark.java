package com.cg5labs.api.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity()
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @SequenceGenerator(
            name = "bookmark_sequence",
            sequenceName = "bookmark_sequence",
            allocationSize = 1
    )
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_sequence")
    private Long id;
    private String url;
    private String description;
    private LocalDate created;

    public Bookmark(String url, String description) {
        this.url = url;
        this.description = description;
        this.created = setCreated();
    }

    public Bookmark() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate setCreated() {
        this.created = LocalDate.now();
        return created;
    }


}