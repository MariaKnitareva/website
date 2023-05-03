package com.billiard.website.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CallsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String input_data;
    private String output_data;
    private String error_message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput_data() {
        return input_data;
    }

    public void setInput_data(String input_data) {
        this.input_data = input_data;
    }

    public String getOutput_data() {
        return output_data;
    }

    public void setOutput_data(String output_data) {
        this.output_data = output_data;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public CallsData(String input_data, String error_message) {
        this.input_data = input_data;
        this.error_message = error_message;
    }
    public CallsData(String input_data, int output_data) {
        this.input_data = input_data;
        this.output_data = String.valueOf(output_data);
    }
    public CallsData() {

    }
}
