package org.sharc.backend;

import lombok.RequiredArgsConstructor;
import org.sharc.backend.model.*;
import org.sharc.backend.repository.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Main {

    private final AddressRepository addressRepository;
    private final CarrierRepository carrierRepository;
    private final NationalCarrierRepository nationalCarrierRepository;
    private final PrivateCarrierRepository privateCarrierRepository;
    private final AccountRepository accountRepository;

    private Address a1;
    private Carrier c1;
    private NationalCarrier nc1;
    private PrivateCarrier pc1;
    private Account acc1;
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
        acc1 = Account.builder()
                .login(c1.getKRS().toString())
                .password("ukulele")
                .build();

        c1.getAddresses().add(a1);
        c1.setAccount(acc1);
        acc1.setCompany(c1);
        a1.setRegisteredFor(c1);
        System.out.println("\n\nCOMPANY KRS");
        System.out.println(c1.getKRS());
        System.out.println("\n\nCOMPANY ACCOUNT");
        System.out.println(acc1.toString());
        carrierRepository.save(c1);
        addressRepository.save(a1);
        accountRepository.save(acc1);

        nc1 = NationalCarrier.builder()
                .KRS("0000929422")
                .name("POLREGIO S.A.")
                .registrationDate(LocalDate.of(2001, 10, 1))
                .licenseRegistration(LocalDate.of(2001, 10, 1))
                .supervisor("Agencja Rozwoju Przemysłu S.A.")
                .build();

        nationalCarrierRepository.save(nc1);
        System.out.println(nc1.toString());

        pc1 = PrivateCarrier.builder()
                .KRS("0000060950")
                .name("Cemet S.A.")
                .registrationDate(LocalDate.of(2001, 11, 15))
                .licenseRegistration(LocalDate.of(2001, 11, 15))
                .registrationBudget(4356892.80)
                .build();

        privateCarrierRepository.save(pc1);
        System.out.println(pc1.toString());
    }
}
