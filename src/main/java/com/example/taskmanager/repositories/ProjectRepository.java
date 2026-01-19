package com.example.taskmanager.repositories;

import com.example.taskmanager.models.Project;
import com.example.taskmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> { List<Project> findByUser(User u); }
