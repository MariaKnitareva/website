package com.billiard.website.models;

public enum TableStatus {
    RESERVED("Забронировано"),
    NOT_SUCCESS("Ошибка бронирования"),
    OPENED("Открыто"),
    CANCELED("Закрыто");
    private String title;
    TableStatus(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

}
