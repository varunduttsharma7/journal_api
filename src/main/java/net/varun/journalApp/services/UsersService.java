package net.varun.journalApp.services;

import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.varun.journalApp.Entities.User;
import net.varun.journalApp.repository.UsersRepository;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository userRepo;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // private static final Logger logger =
    // LoggerFactory.getLogger(UsersService.class);

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
        log.info("info Logging from slf4j");
        log.error("error Logging from slf4j");
        log.warn("warn Logging from slf4j");
        log.debug("debug Logging from slf4j");
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
