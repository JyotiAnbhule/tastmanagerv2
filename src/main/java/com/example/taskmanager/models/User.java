package com.example.taskmanager.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity @Data @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String email;
    @OneToMany(mappedBy = "user")
    private List<Project> projects;
}
