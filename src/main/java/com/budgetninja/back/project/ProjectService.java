package com.budgetninja.back.project;

import com.budgetninja.back.budget.Budget;
import com.budgetninja.back.project.Project;
import com.budgetninja.back.project.ProjectRepository;
import com.budgetninja.back.user.UserRepository;
import com.budgetninja.back.user.User;
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

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project findByUserId(Long userId) {
        return projectRepository.findByBudgetUser_Id(userId);
    }

    public ResponseEntity<Project> addProjectToUser(long userId, Project project) {
        if (project == null || project.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données du projet invalides");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        project.setBudget(user.getBudget());
        Project savedProject = projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProject);
    }

    public Project update(Project project, long id) {
        Project existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject == null) {
            throw new RuntimeException("Project not found");
        } else {
            existingProject.setName(project.getName());
            existingProject.setGoal(project.getGoal());
            return projectRepository.save(existingProject);
        }
    }

    public void delete(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        Budget budget = user.getBudget();
        Project project = budget.getProject();

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
