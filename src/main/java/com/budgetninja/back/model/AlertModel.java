package com.budgetninja.back.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor


public class AlertModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private int periodicite;
    private int threshold;
    private String target;


    @ManyToOne
    private NinjaModel ninjaModel;

}
