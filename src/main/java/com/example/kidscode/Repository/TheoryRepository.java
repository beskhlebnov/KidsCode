package com.example.kidscode.Repository;

import com.example.kidscode.Models.Course;
import com.example.kidscode.Models.Modules;
import com.example.kidscode.Models.Theory;
import com.example.kidscode.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheoryRepository extends JpaRepository<Theory, Long> {
}
