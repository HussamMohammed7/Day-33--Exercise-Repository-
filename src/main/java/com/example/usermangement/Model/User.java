package com.example.usermangement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "role IN ('admin', 'user')")

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be more than 4 characters long")
    @Column(columnDefinition = "varchar(25) not null ")
    private String name;


    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be more than 4 characters long")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String username;



    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(255) not null ")
    private String password;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be a valid format")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;



    @NotEmpty(message = "Role cannot be null")
    @Pattern(regexp = "^(admin|user)$", message = "Role must be either 'user' or 'admin'")
    @Column(columnDefinition = "varchar(15) not null ")
    private String role;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "must be a number")
    @Digits(integer = 3, fraction = 0 , message = "number must be integer")
    @Column (columnDefinition = "int not null ")
    private int age;

}
