package com.sprinthive.coffeshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropsConfig {

    @Autowired
    Environment environment;

    @Value("${coffeshop.service.props.testValue:default.testValue}")
    private String testValue;

    @Bean
    public PropsService getFooService() {
        return new PropsService(environment, testValue);
    }

}
