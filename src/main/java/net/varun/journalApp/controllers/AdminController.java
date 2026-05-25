package net.varun.journalApp.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @Autowired
    private UsersService _usersService;

    @Value("${app.owner.name}")
    public  String ownerName;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = _usersService.findAll();
        log.info("Owner of the app is {}",ownerName);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> AddAdmin(@RequestBody @NonNull User user) {
        _usersService.addAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
