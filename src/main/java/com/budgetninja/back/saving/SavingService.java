package com.budgetninja.back.saving;

import com.budgetninja.back.budget.Budget;
import com.budgetninja.back.saving.Saving;
import com.budgetninja.back.user.User;
import com.budgetninja.back.saving.SavingRepository;
import com.budgetninja.back.user.UserRepository;
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

    public List<Saving> findAll(){
        return savingRepository.findAll();
    }

    public Saving findById(Long id){
        return savingRepository.findById(id).orElse(null);
    }

    public Saving findByUser(long userId){
        return savingRepository.findByBudgetUser_Id(userId);
    }

    public Saving addSavingToUser(long userId, Saving saving) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable."));
        Budget budget = user.getBudget();

        if (budget.getSaving() != null) {
            throw new IllegalStateException("Une épargne est déjà associée à ce budget.");
        }
        saving.setBudget(budget);
        savingRepository.save(saving);
        return saving;
    }

    public Saving addProgrammedSaving (long savingId, Saving saving) {
        Saving existingSaving = savingRepository.findById(savingId).orElseThrow(() -> new IllegalArgumentException("Épargne introuvable."));
        existingSaving.setProgrammedAmount(saving.getProgrammedAmount());
        existingSaving.setProgrammedFrequency(saving.getProgrammedFrequency());
        savingRepository.save(existingSaving);
        return saving;
    }

    public Saving updateProgrammedSaving(long savingId, Saving saving) {
        Saving existingSaving = savingRepository.findById(savingId).orElseThrow(() -> new IllegalArgumentException("Épargne introuvable."));
        existingSaving.setProgrammedAmount(saving.getProgrammedAmount());
        existingSaving.setProgrammedFrequency(saving.getProgrammedFrequency());
        savingRepository.save(existingSaving);
        return saving;
    }

    public Saving update(Saving saving, long id){
        Saving existingSaving = savingRepository.findById(id).orElse(null);
        if (existingSaving == null) {
            throw new RuntimeException("Epargne non trouvée");
        } else {
            existingSaving.setAmount(saving.getAmount());
            return savingRepository.save(existingSaving);
        }
    }

    public void delete(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        Budget budget = user.getBudget();
        Saving saving = budget.getSaving();

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
