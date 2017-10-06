package com.sprinthive.coffeshop;

import com.sprinthive.coffeshop.barista.BaristaConsumer;
import com.sprinthive.coffeshop.barista.BaristaProducer;
import com.sprinthive.coffeshop.barista.BaristaWriteService;
import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaristaConfig {

    @Autowired
    MessagingConfig.MessageChannels messageChannels;

    @Bean
    BaristaConsumer baristaConsumer(BaristaWriteService baristaService) {
        return new BaristaConsumer(baristaService);
    }

    @Bean
    BaristaProducer baristaProducer() {
        return new BaristaProducer(messageChannels.brewStartedProducer(),
                messageChannels.brewFinishedProducer());
    }

    @Bean
    public BaristaWriteService baristaService(BaristaProducer baristaProducer,
                                              CoffeeShopEventRepository coffeeShopEventRepository) {
        return new BaristaWriteService(baristaProducer, coffeeShopEventRepository);
    }
}
