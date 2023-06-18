package com.budgetninja.back.alert;


import com.budgetninja.back.alert.Alert;
import com.budgetninja.back.alert.AlertRepository;
import com.budgetninja.back.user.User;
import com.budgetninja.back.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    public AlertService(AlertRepository alertRepository, UserRepository userRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
    }

    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    public Alert findById(Long id) {
        return alertRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerte non trouvée")
        );
    }

    public Alert findByUserId(Long userId) {
        try {
            return alertRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerte non trouvée");
        }

    }

    public Alert addAlertToUser(long userId, Alert alert) {
        if (alert == null || alert.getThreshold() <= 0 || alert.getPeriodicity() <= 0 || alert.getTarget() == null || alert.getName() == null || alert.getDescription() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données d'alerte invalides");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        if (user.getAlert() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Une alerte est déjà associée à cet utilisateur");
        }

        user.setAlert(alert);
        alert.setUser(user);
        return alertRepository.save(alert);
    }

    public Alert update(long id, Alert alert) {
        Alert existingAlert = alertRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerte non trouvée")
        );
        existingAlert.setName(alert.getName());
        existingAlert.setDescription(alert.getDescription());
        existingAlert.setPeriodicity(alert.getPeriodicity());
        existingAlert.setThreshold(alert.getThreshold());
        existingAlert.setTarget(alert.getTarget());
        return alertRepository.save(alert);
    }

    public void deleteById(Long id) {
        Alert alert = alertRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerte non trouvée")
        );
        User user = alert.getUser();
        user.setAlert(null);
        alertRepository.deleteById(id);
    }
}
