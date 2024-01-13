package org.sharc.impl.repository;

import org.sharc.impl.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    public List<Address> findByCity(String city);

//    @Query("from Address where appartmentNumber > :minNumber")
//    public List<Address> findAddressByNumber(@Param("minNumber") Integer minNumber);
}
