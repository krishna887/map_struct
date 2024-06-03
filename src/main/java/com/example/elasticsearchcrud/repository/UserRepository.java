package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {
    Optional<User> findByUserName(String userName);

}
