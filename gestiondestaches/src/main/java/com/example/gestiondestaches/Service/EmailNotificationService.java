package com.example.gestiondestaches.Service;

import com.example.gestiondestaches.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendNotification(Task task) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("aoayoub20@gmail.com"); // Replace with the actual recipient email
        message.setSubject("Task Deadline Approaching: " + task.getTitle());
        message.setText("Task with title '" + task.getTitle() + "' is due on " + task.getDueDate());

        javaMailSender.send(message);
    }
}
