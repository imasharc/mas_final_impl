package org.sharc.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sharc.backend.model.Carrier;
import org.sharc.backend.model.Company;
import org.sharc.backend.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CarrierRepository carrierRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Carrier c1;
    Manufacturer m1;

    @BeforeEach
    public void initDate() {
        c1 = Carrier.builder()
                .KRS("0000037568")
                .name("PKP Polskie Linie Kolejowe S.A.")
                .address("ul. Targowa 74, 03-734 Warszawa")
                .registrationDate(LocalDate.of(2001, 8, 22))
                .licenseRegistration(LocalDate.of(2004, 10, 10))
                .build();
        m1 = Manufacturer.builder()
                .KRS("0000037568")
                .name("PKP Polskie Linie Kolejowe S.A.")
                .address("ul. Targowa 74, 03-734 Warszawa")
                .registrationDate(LocalDate.of(2001, 8, 22))
                .registrationBudget(30300000.00)
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(companyRepository);
        assertNotNull(carrierRepository);
        assertNotNull(manufacturerRepository);
    }

    @Test
    public void testSaveAll() {
        carrierRepository.save(c1);
        manufacturerRepository.save(m1);
        entityManager.flush();
        Iterable<Company> all = companyRepository.findAll();
        for(Company c : all) {
            System.out.println(c);
        }
        assertEquals(2, companyRepository.count());
    }
}
