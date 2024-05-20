package com.example.elasticsearchcrud.repository;

import com.example.elasticsearchcrud.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
