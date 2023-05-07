package com.vavilov.tracker.tracker.controller;

import com.vavilov.tracker.tracker.entity.TimerEntity;
import com.vavilov.tracker.tracker.service.TimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/timer")
public class TimerController {
    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @PostMapping
    public ResponseEntity createTimer(@RequestParam String title, @RequestParam Long groupID) {
        try {
            return ResponseEntity.ok(timerService.createTimer(title, groupID));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
