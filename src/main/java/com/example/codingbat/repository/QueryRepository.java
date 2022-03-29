package com.example.codingbat.repository;

import com.example.codingbat.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface QueryRepository extends JpaRepository<Query, Integer> {
    boolean existsByText(String text);
    boolean existsByTextAndIdNot(String text, Integer id);
}
