package com.example.codingbat.service;

import com.example.codingbat.dto.CategoryDto;
import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Language;
import com.example.codingbat.entity.Query;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.LanguageRepository;
import com.example.codingbat.repository.QueryRepository;

import com.example.codingbat.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addCategory(CategoryDto categoryDto) {
        Optional<Language> optional = languageRepository.findById(categoryDto.getLanguage_id());
        if (optional.isPresent()) {
            boolean b = queryRepository.existsByText(categoryDto.getQuerytext());
            if (!b) {
                Query query = new Query(categoryDto.getQuerytext(), categoryDto.getGrade(), categoryDto.getSolution());
                queryRepository.save(query);
                List<Query> queries = new ArrayList<>();
                queries.add(query);
                Language languages= optional.get();
                Category category = new Category(categoryDto.getCategoryname(), languages);
                categoryRepository.save(category);
                return new ApiResponse("Ma'lumot saqlandi", true);
            } else {
                return new ApiResponse("Bunday Query topilmadi", false);
            }
        } else {
            return new ApiResponse("Bunday Language topilmadi", false);
        }
    }

    public ApiResponse updateCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optional1 = categoryRepository.findById(id);
        if (optional1.isPresent()) {
            Optional<Language> optional = languageRepository.findById(categoryDto.getLanguage_id());
            if (optional.isPresent()) {
                Category category = optional1.get();
                Language languages= optional.get();
                category.setCategoryname(categoryDto.getCategoryname());
                category.setLanguage(languages);
                categoryRepository.save(category);
                return new ApiResponse("Yangilandi", true);
            } else {
                return new ApiResponse("Bunday Dasturlash tili topilmadi", false);
            }
        } else {
            return new ApiResponse("Bunday Category topilmadi", false);
        }
    }

    public ApiResponse deleteCategory(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } else {
            return new ApiResponse("Bunday Category topilmadi", false);
        }
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getIdCategory(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.orElse(new Category());
    }
}