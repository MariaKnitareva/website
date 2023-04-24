package com.billiard.website.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }
    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("title", "Меню и цены");
        return "menu";
    }
    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("title", "Контактная информация");
        return "contacts";
    }

}
