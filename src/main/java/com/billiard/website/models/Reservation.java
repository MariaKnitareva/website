package com.billiard.website.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer table_id;
    private LocalDateTime dt_from;
    private LocalDateTime dt_to;
    private String table_status;
    private String customer_name;
    private String customer_phone;

    public Reservation() {

    }

    public Reservation(LocalDateTime dt_from, LocalDateTime dt_to, String customer_name, String customer_phone) {
        this.dt_from = dt_from;
        this.dt_to = dt_to;
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public LocalDateTime getDt_from() {
        return dt_from;
    }

    public void setDt_from(LocalDateTime dt_from) {
        this.dt_from = dt_from;
    }

    public LocalDateTime getDt_to() {
        return dt_to;
    }

    public void setDt_to(LocalDateTime dt_to) {
        this.dt_to = dt_to;
    }

    public String getTable_status() {
        return table_status;
    }

    public void setTable_status(String table_status) {
        this.table_status = table_status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
}