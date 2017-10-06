package com.sprinthive.coffeshop.beans;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;

public class BeansWriteService {

    private final BeansProducer beansProducer;
    private final CoffeeShopEventRepository coffeeShopEventRepository;

    public BeansWriteService(BeansProducer beansProducer, CoffeeShopEventRepository coffeeShopEventRepository) {
        this.beansProducer = beansProducer;
        this.coffeeShopEventRepository = coffeeShopEventRepository;
    }

    public void validateBeans(String orderId) {
        CoffeeShopEvent beansValidated = CoffeeShopEvent.builder()
                .orderId(orderId)
                .type("BeansValidated")
                .build();
        coffeeShopEventRepository.save(beansValidated);
        beansProducer.beansValidated(beansValidated);
    }
}
