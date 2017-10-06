package com.sprinthive.coffeshop.barista;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
public class BaristaConsumer {

    BaristaWriteService baristaService;

    public BaristaConsumer(BaristaWriteService baristaService) {
        this.baristaService = baristaService;
    }

    @StreamListener("orderAcceptedConsumer")
    public void orderAccepted(CoffeeShopEvent brewStarted) {
        log.info("Received {}, going to start the brew orderId: {}",
                brewStarted.getType(),
                brewStarted.getOrderId());
        baristaService.startBrewing(brewStarted.getOrderId());
    }
}
