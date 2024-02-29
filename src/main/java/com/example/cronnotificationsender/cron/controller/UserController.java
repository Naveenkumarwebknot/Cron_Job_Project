package com.example.cronnotificationsender.cron.controller;

import com.example.cronnotificationsender.cron.model.User;
import com.example.cronnotificationsender.cron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.Saveuser(user);
        return ResponseEntity.ok().body(createdUser);
    }

//    @GetMapping("/sendmail")
    public void sendDailyMail() {
        userService.sendDailyEmail();
    }

//    @GetMapping("/sendweekly")
    public void sendWeklyMail(){
        userService.sendWeeklyEmail();
    }

//    @GetMapping("/sendmonthly")
    public void sendMonthlyMail(){
        userService.sendMonthlyEmail();
    }



}
