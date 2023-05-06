package com.vavilov.tracker.tracker.controller;

import com.vavilov.tracker.tracker.exception.GroupAlreadyExistException;
import com.vavilov.tracker.tracker.exception.GroupIsNotExistException;
import com.vavilov.tracker.tracker.exception.UserIsNotExistException;
import com.vavilov.tracker.tracker.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Создание новой группы
    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestParam String title, @RequestParam Long userID) {
        try {
            return ResponseEntity.ok(groupService.createGroup(title, userID));
        } catch (GroupAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserIsNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getOneGroup(@RequestParam Long groupID) {
        try {
            return ResponseEntity.ok(groupService.getOneGroup(groupID));
        } catch (GroupIsNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
