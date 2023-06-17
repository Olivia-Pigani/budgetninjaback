package com.budgetninja.back.controller;

import com.budgetninja.back.model.ProjectModel;
import com.budgetninja.back.service.ProjectService;
import jakarta.persistence.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public List<ProjectModel> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectModel findById(Long id) {
        return projectService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public ProjectModel findByUserId(@PathVariable long userId) {
        return projectService.findByUserId(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ProjectModel> addProjectToUser(@PathVariable long userId, @RequestBody ProjectModel projectModel) {
        return projectService.addProjectToUser(userId, projectModel);
    }

    @PutMapping("/{id}")
    public ProjectModel update(@RequestBody ProjectModel projectModel, @PathVariable Long id) {
        return projectService.update(projectModel, id);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) {
        projectService.delete(userId);
    }
}
