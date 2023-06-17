package com.budgetninja.back.project;

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
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(Long id) {
        return projectService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public Project findByUserId(@PathVariable long userId) {
        return projectService.findByUserId(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Project> addProjectToUser(@PathVariable long userId, @RequestBody Project project) {
        return projectService.addProjectToUser(userId, project);
    }

    @PutMapping("/{id}")
    public Project update(@RequestBody Project project, @PathVariable Long id) {
        return projectService.update(project, id);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) {
        projectService.delete(userId);
    }
}
