package com.budgetninja.back.budget;

import com.budgetninja.back.project.Project;
import com.budgetninja.back.saving.Saving;
import com.budgetninja.back.transaction.Transaction;
import com.budgetninja.back.user.User;
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

public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double balance;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(mappedBy = "budget", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private Saving saving;
    @OneToMany(mappedBy = "budget", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();
}
