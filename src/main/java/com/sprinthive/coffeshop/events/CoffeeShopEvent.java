package com.sprinthive.coffeshop.events;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Value
@Builder
@Document(collection = "CoffeeShopEventStream")
public class CoffeeShopEvent<T> {

    Instant creationDate = Instant.now();
    String orderId;
    String type;

    T payload;
}
