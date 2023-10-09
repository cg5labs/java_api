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
    private LocalDate createdOn;
    private String createdBy;


    public Bookmark(String url, String description) {
        this.url = url;
        this.description = description;
        this.createdOn = setCreatedOn();
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
        return createdOn;
    }

    public LocalDate setCreatedOn() {
        this.createdOn = LocalDate.now();
        return createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}