package com.budgetninja.back.category;

import com.budgetninja.back.transaction.Transaction;
import com.budgetninja.back.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @OneToMany(mappedBy = "category")
        private List<Transaction> transactions;
        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonBackReference
        private User user;
}
