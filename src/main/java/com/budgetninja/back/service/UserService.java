package com.budgetninja.back.service;

import com.budgetninja.back.model.RoleModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.RoleRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
    public UserModel findById(Long id){
        return userRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"username not found")
        );
    }
    public UserModel update(UserModel userModel, long id){
        UserModel existingUser= userRepository.findById(id).orElse(null);
        if( existingUser ==null){
            throw new RuntimeException("User not found");

        }else{
            existingUser.setUsername(userModel.getUsername());
            return userRepository.save(existingUser);
        }
}
//    public UserModel addRoleToUser(String username, String rolename){
//        UserModel user = userRepository.findByUsername(username);
//        RoleModel role = roleRepository.findByRole(rolename);
//        user.getRoles().add(role);
//        return user;
//    }
//    public RoleModel addRole(RoleModel role){
//        return roleRepository.save(role);
//    }
//    public UserModel findUserByUsername(String username){
//        return userRepository.findByUsername(username);
//    }
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

}
