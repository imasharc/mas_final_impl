package org.sharc.backend.repository;

import org.sharc.backend.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    public List<Address> findByCity(String city);
    public List<Address> findAll();

//    @Query("from Address where appartmentNumber > :minNumber")
//    public List<Address> findAddressByNumber(@Param("minNumber") Integer minNumber);
}
