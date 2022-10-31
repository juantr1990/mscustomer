package com.nttdata.bootcamp.mscustomer.application.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcamp.mscustomer.infraestructure.CustomerRepository;
import com.nttdata.bootcamp.mscustomer.model.Customer;

import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

	private final  CustomerRepository customerRepository;
	
	static Mono<ServerResponse> notFound =ServerResponse.notFound().build();
	
	@Autowired
	public CustomerHandler(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Mono<ServerResponse> create(ServerRequest serverRequest){
		Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
		return customerMono.flatMap(e->ServerResponse.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(customerRepository.save(e),Customer.class));
	}
	
	
	public Mono<ServerResponse> getAll(ServerRequest serverRequest){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(customerRepository.findAll().log("func"),Customer.class);
	}
	
	
	
}
