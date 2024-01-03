package com.example.gestiondestaches.Controller;

import com.example.gestiondestaches.Model.Task;
import com.example.gestiondestaches.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TasksController {
    @Autowired
    public TaskService taskService;
    @GetMapping("/all")
    public List<Task> getAllTasks() {

        return taskService.getAllTasks();
    }
}
