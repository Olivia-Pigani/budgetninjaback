package com.budgetninja.back.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double balance;
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @OneToOne(mappedBy = "budget", cascade = CascadeType.ALL)
    private SavingModel saving;
    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private TransactionModel transaction;
    @OneToOne(mappedBy = "budget", cascade = CascadeType.ALL)
    private ProjectModel project;
}
