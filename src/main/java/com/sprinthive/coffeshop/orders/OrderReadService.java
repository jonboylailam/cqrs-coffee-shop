package com.sprinthive.coffeshop.orders;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;
import com.sprinthive.coffeshop.orders.entity.CoffeeOrder;

import java.util.List;

public class OrderReadService {

    private final CoffeeShopEventRepository coffeeShopEventRepository;

    public OrderReadService(CoffeeShopEventRepository coffeeShopEventRepository) {
        this.coffeeShopEventRepository = coffeeShopEventRepository;
    }

    public CoffeeOrder get(String orderId) {
        List<CoffeeShopEvent> orderEvents = coffeeShopEventRepository.findAllByOrderIdOrderByCreationDateAsc(orderId);
        if (!orderEvents.isEmpty()) {
            CoffeeOrder coffeeOrder = new CoffeeOrder();
            for (CoffeeShopEvent event : orderEvents) {
                coffeeOrder.apply(event);
            }
            return coffeeOrder;
        }

        throw new OrderNotFoundException("Order not found " + orderId);
    }
}
