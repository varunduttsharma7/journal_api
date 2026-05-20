package net.varun.journalApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import net.varun.journalApp.Entities.User;
import net.varun.journalApp.services.UsersService;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService _usersService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = _usersService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> UpdateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (_usersService.UpdateUser(user, userName)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
