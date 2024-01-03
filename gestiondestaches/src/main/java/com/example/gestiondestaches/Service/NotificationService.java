package com.example.gestiondestaches.Service;

import com.example.gestiondestaches.Model.Task;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    void sendNotification(Task task);
}
