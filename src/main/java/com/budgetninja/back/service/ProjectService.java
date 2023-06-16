package com.budgetninja.back.service;

import com.budgetninja.back.model.ProjectModel;
import com.budgetninja.back.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectModel> findAll() {
        return projectRepository.findAll();
    }

    public ProjectModel findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public ProjectModel save(ProjectModel projectModel) {
        return projectRepository.save(projectModel);
    }

    public ProjectModel update(ProjectModel projectModel, long id) {
        ProjectModel existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject == null) {
            throw new RuntimeException("Project not found");
        } else {
            existingProject.setName(projectModel.getName());
            existingProject.setGoal(projectModel.getGoal());
            return projectRepository.save(existingProject);
        }
    }

    public void delete(long id) {
        projectRepository.deleteById(id);
    }
}
