package com.sprinthive.coffeshop.orders;

import com.sprinthive.coffeshop.Utils;
import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
public class OrdersConsumer {

    private OrderWriteService orderWriteService;

    public OrdersConsumer(OrderWriteService orderWriteService) {
        this.orderWriteService = orderWriteService;
    }

    @StreamListener("beansValidatedConsumer")
    public void beansValidated(CoffeeShopEvent beansValidated) {
        log.info("Received Beans Validated, order accepted orderId: {} type: {}",
                beansValidated.getOrderId(),
                beansValidated.getType());
        orderWriteService.acceptOrder(beansValidated.getOrderId());
    }

    @StreamListener("brewStartedConsumer")
    public void brewStarted(CoffeeShopEvent brewStarted) {
        log.info("Received Brew Started orderId: {}", brewStarted.getOrderId());
    }

    @StreamListener("brewFinishedConsumer")
    public void brewFinished(CoffeeShopEvent brewFinished) {
        log.info("Received Brew Finished orderId: {}", brewFinished.getOrderId());
        Utils.sleepRandomly();
        orderWriteService.deliverOrder(brewFinished.getOrderId());
    }

    @StreamListener("orderDeliveredConsumer")
    public void orderDelivered(CoffeeShopEvent msg) {
        log.info("Received {} orderId: {}",
                msg.getType(),
                msg.getOrderId());
    }
}
