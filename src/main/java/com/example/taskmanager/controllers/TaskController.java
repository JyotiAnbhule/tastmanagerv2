package com.example.taskmanager.controllers;

import com.example.taskmanager.models.*;
import com.example.taskmanager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TaskController {
    @Autowired private ProjectRepository projectRepo;
    @Autowired private TaskRepository taskRepo;
    @Autowired private UserRepository userRepo;

    @GetMapping("/")
    public String dashboard(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        model.addAttribute("projects", projectRepo.findByUser(user));
        return "dashboard";
    }

    @PostMapping("/projects/add")
    public String addProject(@RequestParam String projectName, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        Project p = new Project();
        p.setName(projectName);
        p.setUser(user);
        projectRepo.save(p);
        return "redirect:/";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/add/{projectId}")
    public String addTask(@PathVariable Long projectId, @RequestParam String title) {
        Project p = projectRepo.findById(projectId).orElseThrow();
        Task t = new Task();
        t.setTitle(title);
        t.setStatus("TODO");
        t.setProject(p);
        taskRepo.save(t);
        return "redirect:/";
    }
}