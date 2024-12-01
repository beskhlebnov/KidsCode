package com.example.kidscode.Models;


import com.example.kidscode.Repository.TheoryRepository;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Course course;

    @OneToMany
    private Set<Theory> theories;

    public Theory getTheory(){
        return this.theories.stream().findFirst().get();
    }

}
