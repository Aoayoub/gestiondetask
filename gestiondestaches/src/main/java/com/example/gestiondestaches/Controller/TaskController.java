package com.example.gestiondestaches.Controller;

import com.example.gestiondestaches.Model.InvoiceDataPdfExport;
import com.example.gestiondestaches.Model.Task;
import com.example.gestiondestaches.Repository.TaskRepository;
import com.example.gestiondestaches.Service.NotificationService;
import com.example.gestiondestaches.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
   public TaskService taskService;
   @Autowired
    public TaskRepository taskRepository;
    @GetMapping("/searchTasks")
    public String searchTasks(@RequestParam String keyword, Model model) {
        List<Task> searchResults = taskService.searchTasks(keyword);
        model.addAttribute("listtasks", searchResults);
        return "index";
    }
    @GetMapping("/pdf")
    public ModelAndView exportToPdf() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new InvoiceDataPdfExport());
        //read data from DB
        List<Task> list= taskService.getAllTasks();
        //send to pdfImpl class
        mav.addObject("list", list);
        return mav;
    }
    @GetMapping("/filteredTasks")
    public String getFilteredTasks(@RequestParam(name = "priority", required = false) Task.Priority priority,
                                   @RequestParam(name = "category", required = false) String category,
                                   @RequestParam(name = "status", required = false) Task.Status status,
                                   Model model) {
        List<Task> filteredTasks = taskRepository.findTasksByFilters(priority, category, status);
        model.addAttribute("listtasks", filteredTasks);
        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @GetMapping("/fullcalendar")
    public String showCalendar() {
        return "fullcalendar";
    }
    @GetMapping("/logout")
    public String showLogoutForm() {
        return "logout";
    }


    @GetMapping("/")
        public String viewHomePage(Model model) {
            model.addAttribute("listtasks", taskService.getAllTasks());
            return "index";
        }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "update_task";
    }
    @GetMapping("/showNewTaskForm")
    public String showNewTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "new_task";
    }
    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        // save employee to database
        taskService.createTask(task);
        return "redirect:/";
    }
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable (value = "id") long id) {
        this.taskService.deleteTask(id);
        return "redirect:/";
    }

}
