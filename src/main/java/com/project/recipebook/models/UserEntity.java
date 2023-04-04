package com.project.recipebook.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Column
    @NotNull
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 6, message = "Length")
    @Column
    private String password;

}