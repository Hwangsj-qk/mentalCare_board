package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.CommentDTO;
import com.busanit.mentalCare.dto.Mc_userDto;
import com.busanit.mentalCare.entity.Mc_user;
import com.busanit.mentalCare.service.Mc_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class Mc_userController {

    @Autowired
    private Mc_userService userService;

    @GetMapping
    public ResponseEntity<List<Mc_userDto>> getAllUsers() {
        List<Mc_userDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Mc_userDto> getUserById(@PathVariable String user_id) {
        Mc_userDto user= userService.getUserById(user_id);
        if(user == null ) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping
    ResponseEntity<Mc_userDto> createUser(@RequestBody Mc_userDto user) {
        Mc_userDto createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Mc_userDto> updateUser(@PathVariable String user_id, @RequestBody Mc_user updatedUser) {
        Mc_userDto user = userService.updateUser(user_id, updatedUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String user_id) {
        if (!userService.deleteUser(user_id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}

