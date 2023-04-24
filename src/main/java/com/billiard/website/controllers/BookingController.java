package com.billiard.website.controllers;

import com.billiard.website.Word;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookingController {
    public static Word word;

    @GetMapping("/booking")
    public String booking(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("title", "Бронирование");
        if (name != null) {
            word = new Word(name);
            model.addAttribute("length", word.returnLength());
        } else {
            model.addAttribute("length", "");
        }
        return "booking";
    }
}
