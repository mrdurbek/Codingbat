package com.example.codingbat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerDto {
    @NotNull(message = "answertext bo'sh bo'lmasligi kerak")
    private String answertext;
    @NotNull(message = "query_id bo'sh bo'lmasligi kerak")
    private Integer query_id;
    @NotNull(message = "user_id bo'sh bo'lmasligi kerak")
    private Integer user_id;
    @NotNull(message = "hasstar bo'sh bo'lmasligi kerak")
    private boolean hasstar;
}
