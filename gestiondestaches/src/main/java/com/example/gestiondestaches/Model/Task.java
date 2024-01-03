package com.example.gestiondestaches.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Task")
public class Task {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    public enum Status {
        TODO, IN_PROGRESS, DONE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String category;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
