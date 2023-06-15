package com.budgetninja.back.model;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NinjaModel {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long ninja_id;
    @Column(unique=true)
    private String username;
    private String password;
    private String email;
    private Boolean validated;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="ninja_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<RoleModel> roles;

//    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name="category_user",joinColumns = @JoinColumn(name="ninja_id") ,
//            inverseJoinColumns = @JoinColumn(name="id"))
//    private List<CategoryModel> categories;
}


