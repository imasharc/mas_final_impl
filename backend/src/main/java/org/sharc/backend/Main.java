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
    private final ManufacturerRepository manufacturerRepository;
    private final NationalCarrierRepository nationalCarrierRepository;
    private final PrivateCarrierRepository privateCarrierRepository;
    private final AccountRepository accountRepository;
    private final TrainRepository trainRepository;
    private final SteamTrainRepository steamTrainRepository;
    private final EVRepository evRepository;
    private final CombustionTrainRepository combustionTrainRepository;

    private Address a1;
    private Carrier c1;
    private Manufacturer m1, m2;
    private NationalCarrier nc1;
    private PrivateCarrier pc1;
    private Account acc1;
    private Train t1;
    private SteamTrain st1;
    private EV ev1;
    private CombustionTrain ct1;

    @EventListener
    public void atStart(ContextRefreshedEvent ev) {
        System.out.println("context refreshed");
        Iterable<Address> all = addressRepository.findAll();
        System.out.println(all);

        Set<Address> setOfA = new HashSet<>();
        Set<Train> setOfT = new HashSet<>();
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

        m1 = Manufacturer.builder()
                .KRS("532525")
                .name("macedony")
                .registrationDate(LocalDate.of(2001, 8, 22))
                .registrationBudget(999999.0)
                .build();
        m2 = Manufacturer.builder()
                .KRS("4621523")
                .name("karakna")
                .registrationDate(LocalDate.of(2002, 1, 2))
                .registrationBudget(24153.0)
                .build();
        t1 = Train.builder()
                .numOfWagons(3)
                .registrationDate(LocalDate.of(2001, 11, 15))
                .trainLeadPrice(3000.0)
                .license("43432")
                .model("Pendolino")
                .trainCode("2134")
                .build();

        m1.getTrains().add(t1);
        t1.setProducedBy(m1);

        st1 = SteamTrain.builder()
                .numOfWagons(22)
                .registrationDate(LocalDate.of(2001, 11, 15))
                .trainLeadPrice(12341.0)
                .license("24211")
                .model("Hawera")
                .trainCode("D34SF")
                .steamRate(40)
                .waterCapacity(5000)
                .build();

        m1.getTrains().add(st1);
        st1.setProducedBy(m1);

        ev1 = EV.builder()
                .numOfWagons(22)
                .registrationDate(LocalDate.of(2011, 1, 15))
                .trainLeadPrice(3241.0)
                .license("253243")
                .model("REgnet")
                .trainCode("GFG4423")
                .chargingSpeed(70)
                .batteryType(BatteryType.NiCd)
                .build();

        m2.getTrains().add(ev1);
        ev1.setProducedBy(m2);

        ct1 = CombustionTrain.builder()
                .numOfWagons(18)
                .registrationDate(LocalDate.of(2009, 1, 3))
                .trainLeadPrice(6864)
                .license("35426")
                .model("Carla")
                .trainCode("NM6652")
                .combustionCapacity(500000.0)
                .build();

        m2.getTrains().add(ct1);
        ct1.setProducedBy(m2);

        manufacturerRepository.save(m2);
        manufacturerRepository.save(m1);
        trainRepository.save(t1);
        steamTrainRepository.save(st1);
        evRepository.save(ev1);
        combustionTrainRepository.save(ct1);

        System.out.println(ct1.toString());
        System.out.println(t1.toString());
        System.out.println(m1.toString());
        System.out.println(m2.toString());
    }
}
