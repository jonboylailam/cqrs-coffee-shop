package com.sprinthive.coffeshop.orders.entity;

import com.sprinthive.coffeshop.events.CoffeeShopEvent;
import com.sprinthive.coffeshop.orders.dto.OrderInfo;

import java.util.stream.Stream;

public class CoffeeOrder {

    public String orderId;
    public CoffeeType type;
    public String beanOrigin;
    public CoffeeOrderState state;

    public void apply(CoffeeShopEvent event) {
        String type = event.getType();
        // look at the last event and deduce the state.
        switch (type) {
            case "OrderPlaced" :
                OrderInfo orderInfo = (OrderInfo) event.getPayload();
                this.orderId = event.getOrderId();
                this.type = CoffeeType.fromString(orderInfo.getType());
                this.beanOrigin = orderInfo.getBeanOrigin();
                this.state = CoffeeOrderState.PLACED;
                break;

            case "BeansValidated" :
                this.state = CoffeeOrderState.PLACED;
                break;

            case "NotEnoughBeans" :
                this.state = CoffeeOrderState.CANCELLED;
                break;

            case "BrewStarted" :
                this.state = CoffeeOrder.CoffeeOrderState.STARTED;
                break;

            case "BrewFinished" :
                this.state = CoffeeOrder.CoffeeOrderState.FINISHED;
                break;

            case "OrderDelivered" :
                this.state = CoffeeOrder.CoffeeOrderState.DELIVERED;
                break;
        }
    }

    public enum CoffeeOrderState {
        PLACED,
        ACCEPTED,
        STARTED,
        FINISHED,
        DELIVERED,
        UNKNOWN,
        CANCELLED
    }

    public enum CoffeeType {

        ESPRESSO, POUR_OVER, FRENCH_PRESS;

        public static CoffeeType fromString(final String name) {
            return Stream.of(values())
                    .filter(v -> v.name().equalsIgnoreCase(name))
                    .findAny().orElse(null);
        }
    }
}
