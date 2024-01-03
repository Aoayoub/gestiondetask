package com.example.gestiondestaches.Repository;

import com.example.gestiondestaches.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
// In your TaskRepository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE (:priority IS NULL OR t.priority = :priority) " +
            "AND (:category IS NULL OR t.category LIKE %:category%) " +
            "AND (:status IS NULL OR t.status = :status)")
    List<Task> findTasksByFilters(@Param("priority") Task.Priority priority,
                                  @Param("category") String category,
                                  @Param("status") Task.Status status);
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    List<Task> findByDueDateBeforeAndStatusNot(Date date, Task.Status status);
}

