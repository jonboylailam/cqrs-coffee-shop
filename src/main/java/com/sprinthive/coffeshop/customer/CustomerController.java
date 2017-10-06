package com.sprinthive.coffeshop.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/customer/{name}")
    public Customer findCustomerByName(@PathVariable String name) {
        log.debug("findCustomerByName name: "+ name);
        Customer customer = customerService.findCustomerByName(name);
        return customer;
    }
}
