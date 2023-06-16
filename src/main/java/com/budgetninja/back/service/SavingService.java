package com.budgetninja.back.service;

import com.budgetninja.back.model.SavingModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.SavingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingService {

    private final SavingRepository savingRepository;

    public SavingService(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;
    }

    public List<SavingModel> findAll(){
        return savingRepository.findAll();
    }

    public SavingModel findById(Long id){
        return savingRepository.findById(id).orElse(null);
    }

    public SavingModel save(SavingModel savingModel){
        return savingRepository.save(savingModel);
    }

    public SavingModel update(SavingModel savingModel, long id){
        SavingModel existingSaving = savingRepository.findById(id).orElse(null);
        if (existingSaving == null) {
            throw new RuntimeException("Saving not found");
        } else {
            existingSaving.setAmount(savingModel.getAmount());
            return savingRepository.save(existingSaving);
        }
    }

    public void delete(long id){
        savingRepository.deleteById(id);
    }
}
