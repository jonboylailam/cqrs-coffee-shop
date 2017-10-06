package com.sprinthive.coffeshop.beans;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class BeansProducer {

    private final MessageChannel beansValidatedProducer;

    public BeansProducer(MessageChannel beansValidatedProducer) {
        this.beansValidatedProducer = beansValidatedProducer;
    }

    public void beansValidated(CoffeeShopEvent beansValidated) {
        log.debug("Sending beans validated {} ", beansValidated.getOrderId());
        beansValidatedProducer.send(MessageBuilder.withPayload(beansValidated).build());
    }
}
