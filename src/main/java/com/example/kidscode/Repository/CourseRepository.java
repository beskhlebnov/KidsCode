package com.example.kidscode.Repository;

import com.example.kidscode.Models.Course;
import com.example.kidscode.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(Users teacher);
}
