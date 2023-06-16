package com.budgetninja.back.service;

import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur est survenue lors de la récupération des utilisateurs");
        }
    }

    public UserModel findUserByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    public UserModel updateUser(UserModel userModel, long id) {
        UserModel existingUser = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
        existingUser.setUsername(userModel.getUsername());
        existingUser.setPassword(userModel.getPassword());
        existingUser.setEmail(userModel.getEmail());
        existingUser.setValidated(userModel.getValidated());
        return userRepository.save(existingUser);
    }

    public UserModel createUser(UserModel userModel) {
        String username = userModel.getUsername();
        String email = userModel.getEmail();
        String password = userModel.getPassword();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nom d'utilisateur, mot de passe et email sont requis");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pseudo déjà enregistré");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà enregistré");
        }

        return userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }
    }

}

