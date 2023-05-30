package com.budgetninja.back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="ninjas")
@Getter
@Setter
@NoArgsConstructor

public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;

    private String username;


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List <AlertModel> alertModels;












}
