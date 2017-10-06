package com.sprinthive.coffeshop;

import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;
import com.sprinthive.coffeshop.orders.OrderProducer;
import com.sprinthive.coffeshop.orders.OrderReadService;
import com.sprinthive.coffeshop.orders.OrderWriteService;
import com.sprinthive.coffeshop.orders.OrdersConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersConfig {

    @Bean
    public OrdersConsumer ordersConsumer(OrderWriteService orderCommandService) {
        return new OrdersConsumer(orderCommandService);
    }

    @Bean
    public OrderWriteService orderWriteService(OrderProducer orderProducer,
                                               CoffeeShopEventRepository coffeeShopEventRepository) {
        return new OrderWriteService(orderProducer, coffeeShopEventRepository);
    }

    @Bean
    public OrderReadService orderQueryService(CoffeeShopEventRepository coffeeShopEventRepository) {
        return new OrderReadService(coffeeShopEventRepository);
    }
}
