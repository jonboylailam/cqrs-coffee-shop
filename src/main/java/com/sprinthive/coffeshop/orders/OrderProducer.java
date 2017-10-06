package com.sprinthive.coffeshop.orders;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class OrderProducer {

    private final MessageChannel orderPlacedProducer;
    private final MessageChannel orderAcceptedProducer;
    private final MessageChannel orderDeliveredProducer;

    public OrderProducer(MessageChannel orderPlacedProducer,
                         MessageChannel orderAcceptedProducer,
                         MessageChannel orderDeliveredProducer) {
        this.orderPlacedProducer = orderPlacedProducer;
        this.orderAcceptedProducer = orderAcceptedProducer;
        this.orderDeliveredProducer = orderDeliveredProducer;
    }

    public void sendOrderPlaced(CoffeeShopEvent coffeeShopEvent) {
        log.debug("Sending Order Placed "+ coffeeShopEvent.getOrderId());
        orderPlacedProducer.send(MessageBuilder.withPayload(coffeeShopEvent).build());
    }

    public void sendOrderAccepted(CoffeeShopEvent msg) {
        log.debug("Sending {} orderId: {}", msg.getType(), msg.getOrderId());
        orderAcceptedProducer.send(MessageBuilder.withPayload(msg).build());
    }

    public void sendOrderDelivered(CoffeeShopEvent orderDelivered) {
        log.debug("Sending Order Delivered "+ orderDelivered.getOrderId());
        orderDeliveredProducer.send(MessageBuilder.withPayload(orderDelivered).build());
    }
}
