package com.example.codingbat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    @NotNull(message = "categoryname bo'sh bo'lmasligi kerak")
    private String categoryname;
    @NotNull(message = "querytext bo'sh bo'lmasligi kerak")
    private String querytext;
    @NotNull(message = "grade bo'sh bo'lmasligi kerak")
    private String grade;
    @NotNull(message = "solution bo'sh bo'lmasligi kerak")
    private String solution;
    @NotNull(message = "language_id bo'sh bo'lmasligi kerak")
    private Integer language_id;
}
