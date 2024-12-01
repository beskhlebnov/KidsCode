package com.example.kidscode.Repository;

import com.example.kidscode.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Course, Long> {
}
