package com.sprinthive.coffeshop.barista;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class BaristaProducer {

    private final MessageChannel brewStartedProducer;
    private final MessageChannel brewFinishedProducer;

    public BaristaProducer(MessageChannel brewStartedProducer, MessageChannel brewFinishedProducer) {
        this.brewStartedProducer = brewStartedProducer;
        this.brewFinishedProducer = brewFinishedProducer;
    }

    public void brewStarted(CoffeeShopEvent e) {
        log.debug("Sending brew started {}", e.getOrderId());
        brewStartedProducer.send(MessageBuilder.withPayload(e).build());
    }

    public void brewFinished(CoffeeShopEvent e) {
        log.debug("Sending brew finished {}", e.getOrderId());
        brewFinishedProducer.send(MessageBuilder.withPayload(e).build());
    }
}
