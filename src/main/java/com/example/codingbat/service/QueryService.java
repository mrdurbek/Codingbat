package com.example.codingbat.service;

import com.example.codingbat.entity.Query;
import com.example.codingbat.repository.QueryRepository;
import com.example.codingbat.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryService {
    @Autowired
    QueryRepository queryRepository;

    public ApiResponse addQuery(Query query) {
        boolean b = queryRepository.existsByText(query.getText());
        if (!b) {
            queryRepository.save(query);
            return new ApiResponse("Ma'lumot saqlandi", true);
        } else {
            return new ApiResponse("Bunday Query mavjud", false);
        }
    }

    public ApiResponse updateQuery(Integer id, Query query) {
        Optional<Query> optional = queryRepository.findById(id);
        if (optional.isPresent()) {
            boolean b = queryRepository.existsByTextAndIdNot(query.getText(), id);
            if (!b) {
                Query query1= optional.get();
                query1.setGrade(query.getGrade());
                query1.setSolution(query.getSolution());
                query1.setText(query.getText());
                queryRepository.save(query1);
                return new ApiResponse("Ma'lumot saqlandi", true);
            } else {
                return new ApiResponse("Bunday Query mavjud", false);
            }
        }else {
            return new ApiResponse("Bunday Query topilmadi !!!", false);
        }
    }

    public ApiResponse deleteQuery(Integer id){
        Optional<Query> optional=queryRepository.findById(id);
        if (optional.isPresent()){
            queryRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }else {
            return new ApiResponse("Bunday Query topilmadi", false);
        }
    }

    public List<Query> getAllQuery(){
        return queryRepository.findAll();
    }
    public Query getIdQuery(Integer id){
        Optional<Query> optional=queryRepository.findById(id);
        return optional.orElse(new Query());
    }
}