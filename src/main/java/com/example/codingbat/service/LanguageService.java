package com.example.codingbat.service;

import com.example.codingbat.entity.Language;
import com.example.codingbat.repository.LanguageRepository;
import com.example.codingbat.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;


    public ApiResponse addLanguage(Language language) {
        boolean b = languageRepository.existsByLanguagename(language.getLanguagename());
        if (!b) {
            Language language1 = new Language(language.getLanguagename());
            languageRepository.save(language1);
            return new ApiResponse("Ma'lumot saqlandi", true);
        } else {
            return new ApiResponse("Bunday Dasturlash tili mavjud", false);
        }
    }

    public ApiResponse updateLanguage(Integer id, Language language) {
        Optional<Language> optional = languageRepository.findById(id);
        if (optional.isPresent()) {
            boolean b = languageRepository.existsByLanguagenameAndIdNot(language.getLanguagename(), id);
            if (!b){
            Language language1 = optional.get();
            language1.setLanguagename(language.getLanguagename());
            languageRepository.save(language1);
            return new ApiResponse("Yangilandi",true);
            }else {
                return new ApiResponse("Bunday Dasturlash tili mavjud", false);
            }
        }else {
            return new ApiResponse("Bunday Dasturlash tili topilmadi", false);
        }
    }

    public ApiResponse deleteLanguage(Integer id) {
        Optional<Language> optional = languageRepository.findById(id);
        if (optional.isPresent()) {
            languageRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }else {
            return new ApiResponse("Bunday Dasturlash tili topilmadi", false);
        }
    }

    public List<Language> allLanguage() {
        return languageRepository.findAll();
    }

    public Language idLanguage(Integer id) {
        Optional<Language> optional = languageRepository.findById(id);
        return optional.orElse(new Language());
    }



}
