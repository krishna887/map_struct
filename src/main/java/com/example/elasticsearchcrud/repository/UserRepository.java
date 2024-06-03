package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User>findByUserName(String userName);

}
