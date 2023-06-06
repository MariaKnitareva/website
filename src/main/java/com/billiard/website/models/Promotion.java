package com.billiard.website.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String content;
    private String imgURL;
    private LocalDate start;
    private LocalDate end;

    public Promotion() {
    }
    public Promotion(String name, String type, String content, LocalDate start) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.start = start;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) { this.start = start; }
}
