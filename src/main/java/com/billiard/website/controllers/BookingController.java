package com.billiard.website.controllers;

import com.billiard.website.models.Reservation;
import com.billiard.website.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
public class BookingController {
    @Autowired
    private ReservationRepository reservationRepository;
    private static String success = "Ваш стол успешно зарезервирован!";
    private static String not_success_specific_table = "К сожалению выбронный вами стол уже забронирован";
    private static String not_success_any_table = "К сожалению на данное время все столы уже забронированы";

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("title", "Бронирование");
        LocalDate booking_start = LocalDate.now();
        LocalDate booking_end = booking_start.plusDays(14);
        LocalTime current_time = LocalTime.now();
        model.addAttribute("booking_start", booking_start);
        model.addAttribute("booking_end", booking_end);
        model.addAttribute("current_time", current_time);
        return "booking";
    }
    @PostMapping("booking")
    public String booking(Model model, @RequestParam(name = "table_id") int table_id,
                          @RequestParam(name = "date") LocalDate date,
                          @RequestParam(name = "time") LocalTime time,
                          @RequestParam(name = "duration") Double duration,
                          @RequestParam(name = "name") String customer_name,
                          @RequestParam(name = "phone_number") String customer_phone) {
        LocalDateTime dt_from = LocalDateTime.of(date, time);
        int hours = (int) Math.floor(duration);
        int minutes = 0;
        if (Math.abs(duration - hours) > 0.0001) {
            minutes = 30;
        }
        LocalDateTime dt_to = dt_from.plusHours(hours).plusMinutes(minutes);
        Reservation reservation = new Reservation(dt_from, dt_to, customer_name, customer_phone);
        String answer;
        if (table_id != 0) {
            reservation.setTable_id(table_id);
            if (canBeBooked(reservation)) {
                answer = success;
                reservation.setTable_status("reserved");
            } else {
                answer = not_success_specific_table;
                reservation.setTable_status("not success");
            }
        } else {
            int id = findFreeTable(reservation);
            if (id > 0) {
                answer = success;
                reservation.setTable_status("reserved");
                reservation.setTable_id(id);
            } else {
                answer = not_success_any_table;
                reservation.setTable_status("not success");
            }
        }
        reservationRepository.save(reservation);
        model.addAttribute("answer", answer);
        return "booking";
    }
    public boolean canBeBooked(Reservation reservation) {
        List<Reservation> reservations =  reservationRepository.findReservedTable(reservation.getTable_id(),
                                                                                  reservation.getDt_from(),
                                                                                  reservation.getDt_to());
        if (reservations.size() == 0) {
            return true;
        }
        return false;
    }
    public int findFreeTable(Reservation reservation) {
        int id = 0;
        List<Reservation> reservations =  reservationRepository.findReservedTable(reservation.getDt_from(),
                                                                                  reservation.getDt_to());
        Set<Integer> ids = new HashSet<>();
        for (Reservation res: reservations) {
            ids.add(res.getTable_id());
        }
        for (int i = 1; i <= 6; i++) {
            if (!ids.contains(i)) {
                id = i;
                break;
            }
        }
        return id;
    }

}
