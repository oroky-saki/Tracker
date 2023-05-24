package com.vavilov.tracker.tracker.controller;

import com.vavilov.tracker.tracker.exception.GroupAlreadyExistException;
import com.vavilov.tracker.tracker.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Создание новой группы
    @PostMapping
    public ResponseEntity createGroup(@RequestParam String title, @RequestParam Long userID) {
        try {
            return ResponseEntity.ok(groupService.createGroup(title, userID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getOneGroup(@RequestParam Long groupID) {
        try {
            return ResponseEntity.ok(groupService.getOneGroup(groupID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteGroup(@RequestParam Long groupID) {
        try {
            groupService.deleteGroup(groupID);
            return ResponseEntity.status(HttpStatus.OK).body("deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity changeGroupTitle(@RequestParam Long groupID, @RequestParam String newTitle) {
        try {
            return ResponseEntity.ok(groupService.changeGroupTitle(groupID, newTitle));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getGroupsByUser(@RequestParam Long userID) {
        try {
            return ResponseEntity.ok(groupService.getAllGroupsByUser(userID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
