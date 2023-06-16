package com.budgetninja.back.controller;

import com.budgetninja.back.model.ProjectModel;
import com.budgetninja.back.service.ProjectService;
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

    @PostMapping("")
    public ProjectModel save(@RequestBody ProjectModel projectModel) {
        return projectService.save(projectModel);
    }

    @PutMapping("/{id}")
    public ProjectModel update(@RequestBody ProjectModel projectModel, @PathVariable Long id) {
        return projectService.update(projectModel, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
