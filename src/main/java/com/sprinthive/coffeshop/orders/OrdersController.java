package com.sprinthive.coffeshop.orders;

import com.sprinthive.coffeshop.orders.dto.OrderInfo;
import com.sprinthive.coffeshop.orders.entity.CoffeeOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrdersController {

    @Autowired
    OrderWriteService orderWriteService;

    @Autowired
    OrderReadService orderReadService;

    @RequestMapping(method = RequestMethod.POST, value = "/order/coffee")
    private String orderCoffee(@RequestBody OrderInfo orderInfo) {
        log.debug(orderInfo.getType());
        log.debug(orderInfo.getBeanOrigin());
        orderWriteService.placeOrder(orderInfo);
        return "Order placed";
    }

    @RequestMapping(value = "/order/{orderId}")
    private CoffeeOrder orderCoffee(@PathVariable("orderId") String orderId) {
        if (orderId != null) {
            return orderReadService.get(orderId);
        } else {
            throw new IllegalArgumentException("OrderId is required.");
        }
    }
}
