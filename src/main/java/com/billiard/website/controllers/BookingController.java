package com.billiard.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("title", "Бронирование");
        return "booking";
    }
}
