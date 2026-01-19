package com.example.taskmanager.components;

import com.example.taskmanager.models.*;
import com.example.taskmanager.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;

    public DataInitializer(UserRepository userRepo, ProjectRepository projectRepo) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
    }

    @Override
    public void run(String... args) {
        // Create a default user if not exists
        if (userRepo.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("{noop}feb1472e-bb51-432d-8acd-0fd6bde53fcd"); // Uses your current password
            userRepo.save(user);

            // Create a sample project
            Project project = new Project();
            project.setName("College Khaata Project");
            project.setUser(user);
            projectRepo.save(project);
        }
    }
}