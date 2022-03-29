package com.example.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String categoryname;
    @ManyToOne
    private Language language;


    public Category(String categoryname, Language language) {
        this.categoryname = categoryname;
        this.language = language;

    }
}
