package com.sprinthive.coffeshop.beans;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
public class BeansConsumer {

    private BeansWriteService beansService;

    public BeansConsumer(BeansWriteService beansService) {
        this.beansService = beansService;
    }

    @StreamListener("orderPlacedConsumer")
    public void orderPlaced(CoffeeShopEvent orderPlaced) {
        if ("OrderPlaced".equals(orderPlaced.getType())) {
            log.info("Received Order Placed, going to validate the beans "+ orderPlaced.getOrderId());
            beansService.validateBeans(orderPlaced.getOrderId());
        }
    }
}
