package com.budgetninja.back.saving;

import com.budgetninja.back.budget.Budget;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "savings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount = 0;
    private double programmedAmount = 0;
    private long programmedFrequency = 0;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "budget_id")
    @JsonBackReference
    private Budget budget;
}
