package com.nttdata.bootcamp.mscustomer.application;

import com.nttdata.bootcamp.mscustomer.infraestructure.CustomerRepository;
import com.nttdata.bootcamp.mscustomer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Mono<Customer> insertCustomer(Mono<Customer> customer) {
        return customer.flatMap(customerRepository::save);
    }

    @Override
    public Flux<Customer> retrieveAll() {
        return customerRepository.findAll();
    }

	@Override
	public Mono<Customer> findById(String id) {
		return customerRepository.findById(id);
	}

	@Override
	public Mono<Void> delete(Customer customer) {
		return customerRepository.delete(customer);
	}
}
