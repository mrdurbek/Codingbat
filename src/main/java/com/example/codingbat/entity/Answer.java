package com.example.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String answertext;
    @ManyToOne(optional = false)
    private Query query;
    @ManyToOne(optional = false)
    private User user;
    @Column(nullable = false)
    private boolean hasstar;

    public Answer(String answertext, Query query, User user, boolean hasstar) {
        this.answertext = answertext;
        this.query = query;
        this.user = user;
        this.hasstar = hasstar;
    }
}
