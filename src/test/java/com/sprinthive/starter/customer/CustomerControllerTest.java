package com.sprinthive.starter.customer;

import com.sprinthive.coffeshop.ResponseMsg;
import com.sprinthive.coffeshop.customer.Customer;
import com.sprinthive.coffeshop.customer.CustomerNotFoundException;
import com.sprinthive.coffeshop.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    CustomerService customerService;

    private String endpoint;

    @Before
    public void setup() {
        this.endpoint = "http://localhost:" + port;
    }

    @Test
    public void canFindCustomer() {
        Customer customer = restTemplate.getForObject(endpoint + "/customer/Bob", Customer.class);
        assertThat(customer.getName()).isEqualTo("Bob");
    }

    @Test
    public void canNotFindCustomer() {
        ResponseMsg msg = restTemplate.getForObject(endpoint + "/customer/xyz", ResponseMsg.class);
        assertThat(msg.getMsg()).isNotEmpty();
    }

    @Test
    public void customerNotFoundException() {
        assertThatThrownBy(() -> customerService.findCustomerByName("xyz"))
                .isInstanceOf(CustomerNotFoundException.class);
    }
}
