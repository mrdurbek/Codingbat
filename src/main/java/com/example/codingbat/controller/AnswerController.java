package com.example.codingbat.controller;

import com.example.codingbat.dto.AnswerDto;

import com.example.codingbat.entity.Answer;

import com.example.codingbat.response.ApiResponse;
import com.example.codingbat.service.AnswerService;
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
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("/answer")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PutMapping("/answer/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto) {
        ApiResponse apiResponse = answerService.updateAnswer(id, answerDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/answer")
    public List<Answer> getAllAnswer() {
        return answerService.getAllAnswer();
    }

    @GetMapping("/answer/{id}")
    public Answer getIdAnswer(@PathVariable Integer id) {
        return answerService.getIdAnswer(id);
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
