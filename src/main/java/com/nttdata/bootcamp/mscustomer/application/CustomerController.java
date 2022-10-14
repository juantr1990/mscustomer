package com.nttdata.bootcamp.mscustomer.application;

import com.nttdata.bootcamp.mscustomer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> insertCustomer(@RequestBody Customer customer){
        return customerService.insertCustomer(Mono.just(customer));
    }

    @GetMapping(value = "employee", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> retrieveAll(){
        return customerService.retrieveAll();
    }
}
