package com.project.recipebook.models;


import javax.validation.constraints.Email;
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

    @NotBlank(message = "Email is required")
    @Column
    @NotNull
    @Email
    private String email;

    @NotBlank(message = "Name is required")
    @Column
    @NotNull
    private String name;


    @Column
    private String dateOfBirth;

    @Column
    private String occupation;



    @NotBlank(message = "Password is required")
    @Length(min = 6, message = "Minimum password length - 6 characters")
    @Column
    private String password;

}