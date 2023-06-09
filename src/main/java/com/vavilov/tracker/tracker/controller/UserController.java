package com.vavilov.tracker.tracker.controller;

import com.vavilov.tracker.tracker.exception.InvalidEmailException;
import com.vavilov.tracker.tracker.exception.UserAlreadyExistException;
import com.vavilov.tracker.tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestParam String email, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.createUser(email, password));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
