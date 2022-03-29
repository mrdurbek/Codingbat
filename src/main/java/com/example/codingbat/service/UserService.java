package com.example.codingbat.service;

import com.example.codingbat.entity.User;
import com.example.codingbat.repository.UserRepository;
import com.example.codingbat.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApiResponse addUser(User user) {
        boolean b = userRepository.existsByPassword(user.getPassword());
        if (!b) {
            User user1=new User(user.getEmail(), user.getPassword());
            userRepository.save(user1);
            return new ApiResponse("Ma'lumot saqlandi", true);
        } else {
            return new ApiResponse("Bunday User mavjud", false);
        }
    }

    public ApiResponse updateUser(Integer id, User user) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            boolean b = userRepository.existsByPasswordAndIdNot(user.getPassword(), id);
            if (!b){
                User user1= optional.get();
                user1.setEmail(user.getEmail());
                user1.setPassword(user.getPassword());
                userRepository.save(user1);
                return new ApiResponse("Yangilandi",true);
            }else {
                return new ApiResponse("Bunday User mavjud", false);
            }
        }else {
            return new ApiResponse("Bunday User topilmadi", false);
        }
    }

    public ApiResponse deleteUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }else {
            return new ApiResponse("Bunday User topilmadi", false);
        }
    }

    public List<User> allUser() {
        return userRepository.findAll();
    }

    public User idUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(new User());
    }
}
