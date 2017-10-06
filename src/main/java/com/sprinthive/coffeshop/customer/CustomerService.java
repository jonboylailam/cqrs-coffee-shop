package com.sprinthive.coffeshop.customer;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class CustomerService {

    private final HashMap<String, Customer> custStorage = new HashMap<>();

    public CustomerService() {
        for (String name : Arrays.asList("Bob", "Bill", "Sally")) {
            custStorage.put(name, Customer.builder().name(name).build());
        }
    }

    public Customer findCustomerByName(String name) {
        Customer customer = custStorage.get(name);
        if (customer == null) {
            throw new CustomerNotFoundException("No customer found with the name of ".concat(name));
        }
        return customer;
    }

}
