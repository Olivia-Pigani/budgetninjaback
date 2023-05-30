package com.budgetninja.back.service;

import com.budgetninja.back.model.NinjaModel;
import com.budgetninja.back.repository.NinjaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NinjaService {

 private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }




    public List<NinjaModel> findAll(){
        return ninjaRepository.findAll();
    }


    public NinjaModel findById(Long id){
        return ninjaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the ninja is not found")
        );
    }


    public NinjaModel update(NinjaModel ninjaModel){
        return ninjaRepository.save(ninjaModel);
    }






/*voir code teams*/

    public void deleteById(Long id){
        ninjaRepository.deleteById(id);
    }



}
