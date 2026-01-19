package com.example.taskmanager.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String status; // TODO, IN_PROGRESS, DONE
    @ManyToOne @JoinColumn(name = "project_id")
    private Project project;
}