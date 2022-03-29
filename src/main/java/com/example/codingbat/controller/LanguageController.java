package com.example.codingbat.controller;

import com.example.codingbat.entity.Language;
import com.example.codingbat.response.ApiResponse;
import com.example.codingbat.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @PostMapping("/language")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody Language language) {
        ApiResponse apiResponse = languageService.addLanguage(language);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PutMapping("/language/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody Language language) {
        ApiResponse apiResponse = languageService.updateLanguage(id, language);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @DeleteMapping("/language/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/language")
    public List<Language> getAllLanguage() {
        return languageService.allLanguage();
    }

    @GetMapping("/language/{id}")
    public Language getIdLanguage(@PathVariable Integer id) {
        return languageService.idLanguage(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
