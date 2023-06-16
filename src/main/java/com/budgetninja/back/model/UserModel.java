package com.budgetninja.back.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserModel {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    @Column(unique=true)
    private String email;
    private Boolean validated = false;


    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    private AlertModel alert;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    @JsonBackReference
    private BudgetModel budget;

}


