package com.budgetninja.back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "budgets")

public class BudgetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double balance;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;
    @OneToOne(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonBackReference
    private SavingModel saving;
    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransactionModel> transactions = new ArrayList<>();
    @OneToOne(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ProjectModel project;
}
