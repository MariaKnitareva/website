package com.billiard.website.controllers;

import com.billiard.website.models.Promotion;
import com.billiard.website.models.Reservation;
import com.billiard.website.repo.PromotionRepository;
import com.billiard.website.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private PromotionRepository promotionRepository;


    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("title", "Страница администратора");
        return "admin";
    }
    @GetMapping("/news")
    public String adminNews(Model model) {
        model.addAttribute("title", "Управление новостями");
        return "admin-news";
    }

    @PostMapping("/news")
    public String addNews(@RequestParam(name = "name") String name, @RequestParam(name = "type") String type,
                          @RequestParam(name = "context") String context, @RequestParam(name = "img") String img,
                          @RequestParam(name = "duration")int duration) {
        Promotion promotion = new Promotion(name, type, context, LocalDate.now());
        if (img != null) {
            promotion.setImgURL(img);
        }
        if(duration != 0) {
            promotion.setEnd(promotion.getStart().plusDays(duration));
        }
        promotionRepository.save(promotion);
        return "admin-news";
    }

    @GetMapping("/booking")
    public String adminBooking(Model model) {
        model.addAttribute("title", "Управление бронированием");
        return "admin-booking";
    }

    @PostMapping("/booking")
    public String showReservation(Model model, @RequestParam(name = "date") LocalDate date,
                                  @RequestParam(name = "table_status") String table_status) {
        List<Reservation> reservations = new ArrayList<>();
        switch (table_status) {
            case "Все": reservations = reservationRepository.findTablesByDate(date); break;
            default: reservations = reservationRepository.findTablesByDateAndTS(date, table_status);
        }
        model.addAttribute("reservations", reservations);
        return "admin-booking";
    }
    @GetMapping("/booking/{id}/edit")
    public String editReservation(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("title", "Редоктирование бронирования");
        Optional<Reservation> reservation = reservationRepository.findById(id);
        List<Reservation> res = new ArrayList<>();
        reservation.ifPresent(res::add);
        model.addAttribute("reservation", res);
        return "booking-edit";
    }
    @PostMapping("/booking/{id}/edit")
    public String editReservation(@PathVariable(value = "id") Long id,
                                  @RequestParam(name = "table_status") String table_status) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setTable_status(table_status);
        reservationRepository.save(reservation);
        return "redirect:/admin/booking";
    }
    @GetMapping("/booking/{id}/delete")
    public String deleteReservation(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("title", "Удаление бронирования");
        Optional<Reservation> reservation = reservationRepository.findById(id);
        List<Reservation> res = new ArrayList<>();
        reservation.ifPresent(res::add);
        model.addAttribute("reservation", res);
        return "booking-delete";
    }
    @PostMapping("/booking/{id}/delete")
    public String deleteReservation(@PathVariable(value = "id") Long id) {
        reservationRepository.deleteById(id);
        return "redirect:/admin/booking";
    }
}
