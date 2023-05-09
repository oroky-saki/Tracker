package com.vavilov.tracker.tracker.controller;

import com.vavilov.tracker.tracker.entity.TimerEntity;
import com.vavilov.tracker.tracker.service.TimerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity getOneTimer(@RequestParam Long timerID) {
        try {
            return ResponseEntity.ok(timerService.getOneTimer(timerID));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getTimersByGroup(@RequestParam Long groupID) {
        try {
            return ResponseEntity.ok(timerService.getTimersByGroup(groupID));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteTimer(@RequestParam Long timerID) {
        try {
            timerService.deleteTimer(timerID);
            return ResponseEntity.status(HttpStatus.OK).body("timer deleted");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteTimersByGroup(@RequestParam Long groupID) {
        try {
            timerService.deleteTimersByGroup(groupID);
            return ResponseEntity.status(HttpStatus.OK).body("timers deleted");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/changeTitle")
    public ResponseEntity changeTimerTitle(@RequestParam Long timerID, @RequestParam String newTitle) {
        try {
            return ResponseEntity.ok(timerService.changeTimerTitle(timerID, newTitle));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/changeStatus")
    public ResponseEntity changeTimerStatus(@RequestParam Long timerID, @RequestParam String newStatus) {
        try {
            return ResponseEntity.ok(timerService.changeTimerStatus(timerID, newStatus));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
