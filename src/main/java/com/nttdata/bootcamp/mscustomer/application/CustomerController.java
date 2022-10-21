package com.nttdata.bootcamp.mscustomer.application;

import com.nttdata.bootcamp.mscustomer.model.Customer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RefreshScope
public class CustomerController {

    @Autowired
    CustomerService customerService;
    
    @Value("${message.demo}") 
    private String demoString;

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> insertCustomer(@RequestBody Customer customer){
    	log.info(demoString);
        return customerService.insertCustomer(Mono.just(customer));
    }

    @GetMapping(value = "customer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> retrieveAll(){
        return customerService.retrieveAll();
    }
}
