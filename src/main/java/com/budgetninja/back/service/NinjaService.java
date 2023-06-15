package com.budgetninja.back.service;

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
    private final RoleRepository roleRepository;

    public NinjaService(NinjaRepository ninjaRepository, RoleRepository roleRepository) {
        this.ninjaRepository = ninjaRepository;
        this.roleRepository = roleRepository;

    }
    public List<NinjaModel> findAllUsers() {
        return ninjaRepository.findAll();
    }
    public NinjaModel findUserByUserId(Long ninja_id){
        return ninjaRepository.findById(ninja_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"username not found")
        );
    }

    public NinjaModel updateUser(NinjaModel ninjaModel, long ninja_id){
        NinjaModel existingUser=ninjaRepository.findById(ninja_id).orElse(null);
        if( existingUser ==null){
            throw new RuntimeException("User not found");

        }else{
            existingUser.setUsername(ninjaModel.getUsername());
            return ninjaRepository.save(existingUser);
        }
}
    public NinjaModel save(NinjaModel ninjaModel){
        return ninjaRepository.save(ninjaModel);
    }


    }

