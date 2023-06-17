package com.budgetninja.back.service;

import com.budgetninja.back.model.*;
import com.budgetninja.back.repository.ProjectRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectModel> findAll() {
        return projectRepository.findAll();
    }

    public ProjectModel findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public ProjectModel findByUserId(Long userId) {
        return projectRepository.findByBudgetUser_Id(userId);
    }

    public ResponseEntity<ProjectModel> addProjectToUser(long userId, ProjectModel project) {
        if (project == null || project.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données du projet invalides");
        }

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        project.setBudget(user.getBudget());
        ProjectModel savedProject = projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProject);
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

    public void delete(long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        BudgetModel budget = user.getBudget();
        ProjectModel project = budget.getProject();

        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet non trouvé pour cet utilisateur");
        }

        budget.setProject(null);

        try {
            projectRepository.delete(project);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du budget");
        }
    }
}
