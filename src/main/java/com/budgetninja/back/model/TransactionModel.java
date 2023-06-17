package com.budgetninja.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private String description;
    private double amount;
    private String type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "budget_id")
    private BudgetModel budget;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;
}
