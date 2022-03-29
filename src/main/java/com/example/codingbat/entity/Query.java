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
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)@NotNull(message = "text bo'sh bo'lmasligi kerak")
    private String text;
    @Column(nullable = false)@NotNull(message = "grade bo'sh bo'lmasligi kerak")
    private String grade;
    @Column(nullable = false)@NotNull(message = "solution bo'sh bo'lmasligi kerak")
    private String solution;
    @ManyToOne
    private Category category;

    public Query(String text, String grade, String solution) {
        this.text = text;
        this.grade = grade;
        this.solution = solution;
    }
}
