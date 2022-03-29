package com.example.codingbat.repository;

import com.example.codingbat.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByLanguagename(String languagename);
    boolean existsByLanguagenameAndIdNot(String languagename, Integer id);
}
