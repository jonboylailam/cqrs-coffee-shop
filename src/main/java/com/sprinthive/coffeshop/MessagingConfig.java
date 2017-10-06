package com.sprinthive.coffeshop;

import com.sprinthive.coffeshop.orders.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@EnableBinding(MessagingConfig.MessageChannels.class)
public class MessagingConfig {

    @Autowired
    MessageChannels messageChannels;

    @Bean
    OrderProducer eventProducer() {
        return new OrderProducer(messageChannels.orderPlacedProducer(),
                messageChannels.orderAcceptedProducer(),
                messageChannels.orderDeliveredProducer());
    }
    
    interface MessageChannels {

        @Output
        MessageChannel orderPlacedProducer();

        @Input
        SubscribableChannel orderPlacedConsumer();

        @Output
        MessageChannel orderAcceptedProducer();

        @Input
        SubscribableChannel orderAcceptedConsumer();

        @Output
        MessageChannel orderDeliveredProducer();

        @Input
        SubscribableChannel orderDeliveredConsumer();

        @Output
        MessageChannel beansValidatedProducer();

        @Input
        SubscribableChannel beansValidatedConsumer();

        @Output
        MessageChannel brewStartedProducer();

        @Input
        SubscribableChannel brewStartedConsumer();

        @Output
        MessageChannel brewFinishedProducer();

        @Input
        SubscribableChannel brewFinishedConsumer();
    }
}
