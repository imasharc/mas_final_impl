package org.sharc.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sharc.backend.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Address a1;

    @BeforeEach
    public void initData() {
        a1 = Address.builder()
                .appartmentNumber(null)
                .city("Warsaw")
                .postalCode("01-217")
                .street("ul. Kolejowa 1")
                .build();
    }

    @Test
    public void testRequiredDependencies() {
      assertNotNull(addressRepository);
    }

    @Test
    public void testFetchAddresses() {
        Iterable<Address> all = addressRepository.findAll();
        for(Address a : all) {
            System.out.println(a);
        }
    }

    @Test
    public void testSaveAddress() {
        addressRepository.save(a1);
        entityManager.flush();
        long count = addressRepository.count();
        assertEquals(3, count);
    }

    @Test
    public void testSaveInvalidAddressPostalCode() {
        assertThrows(ConstraintViolationException.class, () -> {
            a1.setPostalCode("123");
            addressRepository.save(a1);
            entityManager.flush();
        });
    }

    @Test
    public void testFindByCity() {
        List<Address> warsaw = addressRepository.findByCity("Warsaw");
        assertEquals(1, warsaw.size());
        System.out.println(warsaw);
    }
}