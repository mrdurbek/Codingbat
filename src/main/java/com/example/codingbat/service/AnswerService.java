package com.example.codingbat.service;

import com.example.codingbat.dto.AnswerDto;
import com.example.codingbat.entity.Answer;
import com.example.codingbat.entity.Query;
import com.example.codingbat.entity.User;
import com.example.codingbat.repository.AnswerRepository;
import com.example.codingbat.repository.QueryRepository;
import com.example.codingbat.repository.UserRepository;
import com.example.codingbat.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnswerRepository answerRepository;

    public ApiResponse addAnswer(AnswerDto answerDto) {
        Optional<Query> optional = queryRepository.findById(answerDto.getQuery_id());
        if (optional.isPresent()) {
            Optional<User> optional1 = userRepository.findById(answerDto.getUser_id());
            if (optional1.isPresent()) {
                Answer answer = new Answer(answerDto.getAnswertext(), optional.get(), optional1.get(), answerDto.isHasstar());
                answerRepository.save(answer);
                return new ApiResponse("Ma'lumot saqlandi", true);
            } else {
                return new ApiResponse("Bunday User topilmadi", false);
            }
        } else {
            return new ApiResponse("Bunday Query topilmadi ", false);
        }
    }
    public ApiResponse updateAnswer(Integer id, AnswerDto answerDto) {
        Optional<Answer> optional2 = answerRepository.findById(id);
        if (optional2.isPresent()) {
            Optional<Query> optional = queryRepository.findById(answerDto.getQuery_id());
            if (optional.isPresent()) {
                Optional<User> optional1 = userRepository.findById(answerDto.getUser_id());
                if (optional1.isPresent()) {
                    Answer answer = optional2.get();
                    answer.setAnswertext(answerDto.getAnswertext());
                    answer.setQuery(optional.get());
                    answer.setUser(optional1.get());
                    answer.setHasstar(answerDto.isHasstar());
                    answerRepository.save(answer);
                    return new ApiResponse("Yangilandi", true);
                } else {
                    return new ApiResponse("Bunday User topilmadi", false);
                }
            } else {
                return new ApiResponse("Bunday Query topilmadi ", false);
            }
        } else {
            return new ApiResponse("Bunday Answer topilmadi ", false);
        }
    }
    public ApiResponse deleteAnswer(Integer id) {
        Optional<Answer> optional = answerRepository.findById(id);
        if (optional.isPresent()) {
            answerRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } else {
            return new ApiResponse("Bunday Answer topilmadi ", false);
        }
    }
    public List<Answer> getAllAnswer() {
        return answerRepository.findAll();
    }
    public Answer getIdAnswer(Integer id) {
        Optional<Answer> optional = answerRepository.findById(id);
        return optional.orElse(new Answer());
    }
}
