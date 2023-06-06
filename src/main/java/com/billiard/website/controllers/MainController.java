package com.billiard.website.controllers;
import com.billiard.website.models.Promotion;
import com.billiard.website.repo.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class MainController {
    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        Iterable<Promotion> promotions = promotionRepository.findAll();
        List<Promotion> result = StreamSupport.stream(promotions.spliterator(), false).collect(Collectors.toList());
        Collections.reverse(result);
        model.addAttribute("promotions", result);
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
