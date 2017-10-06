package com.sprinthive.coffeshop.orders;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;
import com.sprinthive.coffeshop.orders.dto.DeliveryInfo;
import com.sprinthive.coffeshop.orders.dto.OrderInfo;
import com.sprinthive.coffeshop.orders.dto.Person;

import java.util.UUID;

public class OrderWriteService {

    private final OrderProducer orderProducer;
    private final CoffeeShopEventRepository coffeeShopEventRepository;

    public OrderWriteService(OrderProducer orderProducer,
                             CoffeeShopEventRepository coffeeShopEventRepository) {
        this.orderProducer = orderProducer;
        this.coffeeShopEventRepository = coffeeShopEventRepository;
    }

    public String placeOrder(OrderInfo orderInfo) {
        String orderId = UUID.randomUUID().toString();
        CoffeeShopEvent orderPlaced = CoffeeShopEvent.builder()
                .orderId(orderId)
                .type("OrderPlaced")
                .payload(orderInfo)
                .build();
        coffeeShopEventRepository.save(orderPlaced);
        orderProducer.sendOrderPlaced(orderPlaced);
        return orderId;
    }

    public void acceptOrder(String orderId) {
        CoffeeShopEvent orderPlaced = CoffeeShopEvent.builder()
                .orderId(orderId)
                .type("OrderAccepted").build();
        coffeeShopEventRepository.save(orderPlaced);
        orderProducer.sendOrderAccepted(orderPlaced);
    }

    public void deliverOrder(String orderId) {
        Person deliveredTo = Person.builder()
                .name("Sammy the seal").build();
        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .deliveredTo(deliveredTo)
                .build();

        CoffeeShopEvent orderDelivered = CoffeeShopEvent.builder()
                .orderId(orderId)
                .type("OrderDelivered")
                .payload(deliveryInfo)
                .build();
        coffeeShopEventRepository.save(orderDelivered);
        orderProducer.sendOrderDelivered(orderDelivered);
    }
}
