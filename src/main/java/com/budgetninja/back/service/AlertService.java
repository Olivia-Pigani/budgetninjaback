package com.budgetninja.back.service;


import com.budgetninja.back.model.AlertModel;
import com.budgetninja.back.repository.AlertRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class AlertService {

    private AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }





    public List<AlertModel> findAll(){
        return alertRepository.findAll();
    }


    public AlertModel findById(Long id){
        return alertRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found")
        );
    }


    public AlertModel update(AlertModel alertModel){
        return alertRepository.save(alertModel);
    }



    public void deleteById(Long id){
        alertRepository.deleteById(id);
    }








}
