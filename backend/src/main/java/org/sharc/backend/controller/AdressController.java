package org.sharc.backend.controller;

import org.sharc.backend.model.Address;
import org.sharc.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/adresses")
public class AdressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public List<Address> getAllAdresses(){
        return addressRepository.findAll();
    }
}
