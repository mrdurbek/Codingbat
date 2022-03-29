package com.example.codingbat.controller;


import com.example.codingbat.entity.Query;
import com.example.codingbat.response.ApiResponse;
import com.example.codingbat.service.QueryService;
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
public class QueryController {
    @Autowired
    QueryService queryService;

    @PostMapping("/query")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody Query query) {
        ApiResponse apiResponse = queryService.addQuery(query);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PutMapping("/query/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody Query query) {
        ApiResponse apiResponse = queryService.updateQuery(id,query);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @DeleteMapping("/query/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = queryService.deleteQuery(id);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/query")
    public List<Query> getAllQuery() {
        return queryService.getAllQuery();
    }

    @GetMapping("/query/{id}")
    public Query getIdLanguage(@PathVariable Integer id) {
        return queryService.getIdQuery(id);
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
