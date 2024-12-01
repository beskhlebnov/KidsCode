package com.example.kidscode.Models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String difficult;

    @ManyToOne()
    private Language language;
}
