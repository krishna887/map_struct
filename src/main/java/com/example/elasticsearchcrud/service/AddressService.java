package com.example.elasticsearchcrud.service;

import com.example.elasticsearchcrud.model.Address;
import com.example.elasticsearchcrud.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public Address addAddress(Address address) {
        return  addressRepository.save(address);
    }

}
