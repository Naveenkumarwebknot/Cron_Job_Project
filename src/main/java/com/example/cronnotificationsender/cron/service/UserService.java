package com.example.cronnotificationsender.cron.service;

import com.example.cronnotificationsender.cron.model.Subscribed;
import com.example.cronnotificationsender.cron.model.User;
import com.example.cronnotificationsender.cron.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;




@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    JavaMailSender emailSender;


    public List<User> getAllUser(){
        logger.info("Hello World");
        return userRepository.findAll();
    }

    public User Saveuser(User user){
        logger.info("Hello World");
        return userRepository.save(user);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("karlmarxa1b2@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        logger.info("Mail Sent ");
    }

    @Scheduled(cron = "0 43 18 * * *")
    public void sendDailyEmail() {
        List<User> users = userRepository.findAll();
        logger.info("Daily email sent");
        users.forEach(user -> {
            String subject = "Daily Summary";
            String text = "This is your daily summary email.";
            if(user.getSubscribe()==Subscribed.DAILY)
                sendEmail(user.getEmail(), subject, text);
        });
    }

    @Scheduled(cron = "0 35 18 * * SUN")
    public void sendWeeklyEmail() {

        List<User> users = userRepository.findAll();
        logger.info("Hello World");
        users.forEach(user -> {
            String subject = "Weekly BlogPost";
            String text = "This is your daily summary email.";
            if(user.getSubscribe()== Subscribed.WEEKLY)
                sendEmail(user.getUsername(),text,subject);
        });
    }



    @Scheduled(cron = "0 35 18 1 * *")
    public void sendMonthlyEmail() {

        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            String subject = "Weekly BlogPost";
            String text = "This is your daily summary email";
            if(user.getSubscribe()==Subscribed.MONTHLY)
                sendEmail(user.getUsername(),text,subject);
        });
    }


}
