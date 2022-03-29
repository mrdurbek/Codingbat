package com.example.codingbat.controller;

import com.example.codingbat.dto.CategoryDto;
import com.example.codingbat.entity.Category;

import com.example.codingbat.response.ApiResponse;
import com.example.codingbat.service.CategoryService;
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
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.updateCategory(id,categoryDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/category")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{id}")
    public Category getIdCategory(@PathVariable Integer id) {
        return categoryService.getIdCategory(id);
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
