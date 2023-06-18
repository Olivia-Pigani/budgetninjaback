package com.budgetninja.back.user;

import com.budgetninja.back.alert.Alert;
import com.budgetninja.back.budget.Budget;
import com.budgetninja.back.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
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
    private Alert alert;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Budget budget;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Category> categories;


}


