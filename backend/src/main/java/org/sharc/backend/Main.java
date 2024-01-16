package org.sharc.backend;

import lombok.RequiredArgsConstructor;
import org.sharc.backend.model.Address;
import org.sharc.backend.model.Carrier;
import org.sharc.backend.model.Company;
import org.sharc.backend.repository.AddressRepository;
import org.sharc.backend.repository.CarrierRepository;
import org.sharc.backend.repository.CompanyRepository;
import org.sharc.backend.repository.ManufacturerRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Main {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final CarrierRepository carrierRepository;
    private final ManufacturerRepository manufacturerRepository;
    private Address a1;
    private Carrier c1;
    private Set<Address> setOfA = new HashSet<>();

    @EventListener
    public void atStart(ContextRefreshedEvent ev) {
        System.out.println("context refreshed");
        Iterable<Address> all = addressRepository.findAll();
        System.out.println(all);

        Set<Address> setOfA = new HashSet<>();
        a1 = Address.builder()
                .appartmentNumber(null)
                .city("Warsaw")
                .postalCode("01-217")
                .street("ul. Kolejowa 1")
                .build();
        setOfA.add(a1);
        c1 = Carrier.builder()
                .KRS("0000037568")
                .name("PKP Polskie Linie Kolejowe S.A.")
                .registrationDate(LocalDate.of(2001, 8, 22))
                .licenseRegistration(LocalDate.of(2004, 10, 10))
                .build();
        c1.getAddresses().add(a1);
        carrierRepository.save(c1);
        a1.setRegisteredFor(c1);
        addressRepository.save(a1);
        System.out.println("\n\nCOMPANIES");

        Optional<Company> companies = companyRepository.findById(c1.getId());

        System.out.println(companies.get().getAddresses());
    }
}
