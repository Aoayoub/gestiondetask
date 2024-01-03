package com.example.gestiondestaches.Service;

import com.example.gestiondestaches.Model.Task;
import com.example.gestiondestaches.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    @Autowired
    public TaskRepository taskRepository;
@Autowired
public  NotificationService notificationService;
    @Scheduled(fixedRate = 60000) // Run every minute (adjust as needed)
    public void checkAndSendNotifications() {
        List<Task> tasks = taskRepository.findByDueDateBeforeAndStatusNot(
                new Date(), Task.Status.DONE
        );

        for (Task task : tasks) {
            notificationService.sendNotification(task);
        }
    }
    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
    public List<Task>getAllTasks()
    {
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id)
    {
        Optional <Task> optional = taskRepository.findById(id);
        Task task = null;
        if (optional.isPresent()) {
            task = optional.get();
        } else {
            throw new RuntimeException(" task not found for id :: " + id);
        }
        return task;

    }
    public void createTask(Task task)
    {
        taskRepository.save(task);
    }
    public void deleteTask(Long id)
    {
        taskRepository.deleteById(id);
    }
}
