package net.varun.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import net.varun.journalApp.Entities.User;
import net.varun.journalApp.services.UsersService;

@RestController
@RequestMapping("public")
public class PublicController {
    @Autowired
    private UsersService _usersService;

    @PostMapping("users")
    public ResponseEntity<?> AddUser(@RequestBody @NonNull User user) {
        _usersService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("health")
    public String healthCheck() {
        return "Ok";
    }
}
