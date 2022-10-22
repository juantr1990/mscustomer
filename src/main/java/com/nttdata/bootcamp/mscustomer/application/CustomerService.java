package com.nttdata.bootcamp.mscustomer.application;

import com.nttdata.bootcamp.mscustomer.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> insertCustomer(Mono<Customer> customer);
    Flux<Customer> retrieveAll();
    
    Mono<Customer> findById(String id);
    Mono<Void> delete(Customer customer);
}
