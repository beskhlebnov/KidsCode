package com.example.kidscode.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Kids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;

    @ManyToOne()
    private Users user;

    @ManyToOne()
    private Users parent;

    public Kids(int age, Users user, Users parent) {
        this.age = age;
        this.user = user;
        this.parent = parent;
    }

    public Kids(Long id) {this.id = id;}
}
