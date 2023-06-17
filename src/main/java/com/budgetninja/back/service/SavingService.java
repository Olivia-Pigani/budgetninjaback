package com.budgetninja.back.service;

import com.budgetninja.back.model.BudgetModel;
import com.budgetninja.back.model.SavingModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.SavingRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SavingService {

    private final SavingRepository savingRepository;
    private final UserRepository userRepository;

    public SavingService(SavingRepository savingRepository, UserRepository userRepository) {
        this.savingRepository = savingRepository;
        this.userRepository = userRepository;
    }

    public List<SavingModel> findAll(){
        return savingRepository.findAll();
    }

    public SavingModel findById(Long id){
        return savingRepository.findById(id).orElse(null);
    }

    public SavingModel findByUser(long userId){
        return savingRepository.findByBudgetUser_Id(userId);
    }

    public SavingModel addSavingToUser(long userId, SavingModel saving) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
        BudgetModel budget = user.getBudget();

        if (budget.getSaving() != null) {
            throw new IllegalStateException("Une épargne est déjà associée à ce budget.");
        }
        saving.setBudget(budget);
        savingRepository.save(saving);
        return saving;
    }

    public SavingModel update(SavingModel savingModel, long id){
        SavingModel existingSaving = savingRepository.findById(id).orElse(null);
        if (existingSaving == null) {
            throw new RuntimeException("Epargne non trouvée");
        } else {
            existingSaving.setAmount(savingModel.getAmount());
            return savingRepository.save(existingSaving);
        }
    }

    public void delete(long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        BudgetModel budget = user.getBudget();
        SavingModel saving = budget.getSaving();

        if (saving == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Épargne non trouvée pour cet utilisateur");
        }

        budget.setSaving(null);

        try {
            savingRepository.delete(saving);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la suppression de l'épargne");
        }
    }

}
