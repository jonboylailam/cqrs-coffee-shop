package com.sprinthive.coffeshop;

import com.sprinthive.coffeshop.beans.BeansConsumer;
import com.sprinthive.coffeshop.beans.BeansProducer;
import com.sprinthive.coffeshop.beans.BeansWriteService;
import com.sprinthive.coffeshop.events.CoffeeShopEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Autowired
    MessagingConfig.MessageChannels messageChannels;

    @Bean
    BeansConsumer beansConsumer(BeansWriteService beansService) {
        return new BeansConsumer(beansService);
    }

    @Bean
    BeansProducer beansProducer() {
        return new BeansProducer(messageChannels.beansValidatedProducer());
    }

    @Bean
    public BeansWriteService beansService(BeansProducer beansProducer,
                                          CoffeeShopEventRepository coffeeShopEventRepository) {
        return new BeansWriteService(beansProducer, coffeeShopEventRepository);
    }

}
