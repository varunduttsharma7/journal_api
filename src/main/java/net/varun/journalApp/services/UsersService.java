package net.varun.journalApp.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.varun.journalApp.Entities.Journal;
import net.varun.journalApp.Entities.User;
import net.varun.journalApp.repository.JournalRepository;
import net.varun.journalApp.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepo;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public void saveEntry(@NonNull User user) {
        userRepo.save(user);
    }

    public void saveUser(@NonNull User user) {
        user.setRoles(Arrays.asList("USER"));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void addAdmin(@NonNull User user) {
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(String id) {
        User user = userRepo.findById(new ObjectId(id)).get();
        return user;
    }

    public User findByUserName(String userName) {
        User user = userRepo.findByUserName(userName);
        return user;
    }

    public boolean UpdateUser(User user, String name) {
        User dbUser = userRepo.findByUserName(name);
        if (dbUser != null) {
            dbUser.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(dbUser);
            return true;
        }
        return false;
    }
}
