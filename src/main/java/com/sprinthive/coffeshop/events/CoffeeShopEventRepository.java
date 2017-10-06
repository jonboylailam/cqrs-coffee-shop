package com.sprinthive.coffeshop.events;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoffeeShopEventRepository extends MongoRepository<CoffeeShopEvent, String> {

    CoffeeShopEvent findCoffeeShopEventByOrderIdAndAndType(String orderId, String type);

    List<CoffeeShopEvent> findAllByOrderIdOrderByCreationDateAsc(String orderId);
}
