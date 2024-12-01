package com.example.kidscode.Models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;

    @ManyToOne
    private Users teacher;

}
