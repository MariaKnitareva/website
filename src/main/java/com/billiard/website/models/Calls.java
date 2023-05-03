package com.billiard.website.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Calls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private LocalDateTime created_dt;

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

    public LocalDateTime getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(LocalDateTime created_dt) {
        this.created_dt = created_dt;
    }

    public Calls(String url) {
        this.url = url;
        this.created_dt = LocalDateTime.now();
    }
    public Calls() {

    }
}
