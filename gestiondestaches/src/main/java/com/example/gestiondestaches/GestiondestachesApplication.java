package com.example.gestiondestaches;

import com.example.gestiondestaches.Model.Task;
import com.example.gestiondestaches.Service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@SpringBootApplication
@EnableScheduling
public class GestiondestachesApplication {
@Autowired
public EmailNotificationService emailNotificationService;
	public static void main(String[] args) {
		SpringApplication.run(GestiondestachesApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendmail() throws ParseException {SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dueDate =dateFormat.parse("02/01/2024");
		Task task =new Task(1L,"blabla","blabla",null,dateFormat.parse("02/01/2024"),null,null,null);
		emailNotificationService.sendNotification(task);
	}
}

