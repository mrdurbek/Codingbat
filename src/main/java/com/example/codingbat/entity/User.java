package com.example.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "foydalanuvchi")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)@NotNull(message = "password bo'sh bo'lishi kerak emas")
    private String email;
    @Column(nullable = false,unique = true)@NotNull(message = "password bo'sh bo'lishi kerak emas")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
