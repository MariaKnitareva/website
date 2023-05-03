package com.billiard.website.controllers;

import com.billiard.website.models.Calls;
import com.billiard.website.models.CallsData;
import com.billiard.website.models.Word;
import com.billiard.website.repo.CallsDataRepository;
import com.billiard.website.repo.CallsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class BookingController {
    public static Word word;
    @Autowired
    private CallsRepository callsRepository;
    @Autowired
    private CallsDataRepository callsDataRepository;

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("title", "Бронирование");
        return "booking";
    }
    @GetMapping(value = "/booking", params = {"word"})
    public String booking(@RequestParam(value = "word", required = false) String inputWord, Model model) {
        word = new Word(inputWord);
        Calls calls = new Calls("http://localhost:8080/booking");
        CallsData callsData;
        if (word.isCorrect()) {
            model.addAttribute("length", word.returnLength());
            callsData = new CallsData(inputWord, word.returnLength());
        } else {
            model.addAttribute("length", word.returnError());
            callsData = new CallsData(inputWord, word.returnError());
        }
        callsRepository.save(calls);
        callsDataRepository.save(callsData);
        return "booking";
    }
    @GetMapping(value = "/booking", params = {"count"})
    public String booking(@RequestParam(value = "count", required = false) int count, Model model) {
        Long bdSize = callsRepository.count();
        ArrayList<Calls> calls = new ArrayList<>();
        ArrayList<CallsData> callsData = new ArrayList<>();
        for (int i = 1; i <= count; i ++) {
            Optional<Calls> optionalCalls = callsRepository.findById(Long.valueOf(i));
            Optional<CallsData> optionalCallsData = callsDataRepository.findById(Long.valueOf(i));
            optionalCalls.ifPresent(calls::add);
            optionalCallsData.ifPresent(callsData::add);
        }
        model.addAttribute("calls", calls);
        model.addAttribute("callsData", callsData);
        return "booking";
    }
}
