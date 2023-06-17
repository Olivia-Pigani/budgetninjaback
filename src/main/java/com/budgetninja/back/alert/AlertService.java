package com.budgetninja.back.alert;


import com.budgetninja.back.alert.Alert;
import com.budgetninja.back.alert.AlertRepository;
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





    public List<Alert> findAll(){
        return alertRepository.findAll();
    }


    public Alert findById(Long id){
        return alertRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found")
        );
    }


    public Alert update(Alert alert){
        return alertRepository.save(alert);
    }



    public void deleteById(Long id){
        alertRepository.deleteById(id);
    }








}
