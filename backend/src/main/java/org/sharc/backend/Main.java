package org.sharc.backend;

import lombok.RequiredArgsConstructor;
import org.sharc.backend.model.Address;
import org.sharc.backend.repository.AddressRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Main {

    private final AddressRepository addressRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent ev) {
        System.out.println("context refreshed");
        Iterable<Address> all = addressRepository.findAll();
        System.out.println(all);
    }
}
