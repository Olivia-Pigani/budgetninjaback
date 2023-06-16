package com.budgetninja.back.service;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.model.NinjaModel;
import com.budgetninja.back.repository.RoleRepository;
import com.budgetninja.back.repository.NinjaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NinjaService {
    private final NinjaRepository ninjaRepository;
    public NinjaService(NinjaRepository ninjaRepository, RoleRepository roleRepository) {
        this.ninjaRepository = ninjaRepository;


    }
    public List<NinjaModel> findAllNinja() {
        return ninjaRepository.findAll();
    }
    public NinjaModel findNinjaById(Long ninja_id){
        return ninjaRepository.findById(ninja_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ninja not found")
        );
    }
    public NinjaModel updateNinja(NinjaModel ninjaModel, long ninja_id){
        NinjaModel existingUser=ninjaRepository.findById(ninja_id).orElse(null);
        if( existingUser ==null){
            throw new RuntimeException("User not found");

        }else{
            existingUser.setUsername(ninjaModel.getUsername());
            return ninjaRepository.save(existingUser);
        }
}
    public void deleteNinjaById(Long ninja_id){
        ninjaRepository.deleteById(ninja_id);
    }
    public NinjaModel save(NinjaModel ninjaModel){
        return ninjaRepository.save(ninjaModel);
    }


    }

