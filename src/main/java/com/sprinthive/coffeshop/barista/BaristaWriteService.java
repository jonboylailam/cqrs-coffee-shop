package com.sprinthive.coffeshop.barista;

import com.sprinthive.coffeshop.Utils;
import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaristaWriteService {

    private final BaristaProducer baristaProducer;
    private final CoffeeShopEventRepository coffeeShopEventRepository;


    public BaristaWriteService(BaristaProducer baristaProducer,
                               CoffeeShopEventRepository coffeeShopEventRepository) {
        this.baristaProducer = baristaProducer;
        this.coffeeShopEventRepository = coffeeShopEventRepository;
    }

    public void startBrewing(String orderId) {
        CoffeeShopEvent brewStarted = CoffeeShopEvent.builder()
                .orderId(orderId)
                .type("BrewStarted")
                .build();
        coffeeShopEventRepository.save(brewStarted);
        this.baristaProducer.brewStarted(brewStarted);

        // get the beans and then when the brew is finished send brewFinished
        Utils.sleepRandomly();

        CoffeeShopEvent brewFinished = CoffeeShopEvent.builder()
                .orderId(orderId)
                .type("BrewFinished")
                .build();
        coffeeShopEventRepository.save(brewFinished);
        this.baristaProducer.brewFinished(brewFinished);
    }
}
