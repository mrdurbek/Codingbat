package com.example.codingbat.repository;

import com.example.codingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByPassword(String password);
    boolean existsByPasswordAndIdNot(String password, Integer id);
}
