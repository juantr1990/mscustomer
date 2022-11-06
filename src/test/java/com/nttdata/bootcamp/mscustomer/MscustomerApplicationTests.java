package com.nttdata.bootcamp.mscustomer;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.bootcamp.mscustomer.application.CustomerController;
import com.nttdata.bootcamp.mscustomer.application.CustomerService;
import com.nttdata.bootcamp.mscustomer.model.Customer;

import  static  org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class MscustomerApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private CustomerService customerService;
	
	@Test
	void crearTest() {
		Mono<Customer> customer = Mono.just(new Customer("4444", "4565456", "JUAN", "TORRES","E"));
			when(customerService.insertCustomer(customer)).thenReturn(customer);
			webTestClient.post().uri("/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(customer), Customer.class)
			.exchange()
			.expectStatus().isCreated();
	}
	
	//@Test
	void getAllTest() {
		/**
		Mono<List<Customer>> customerm = Mono.just(Arrays.asList(new Customer("4444", "4565456", "JUAN", "TORRES","E"),
				new Customer("4444", "4565456", "JUAN", "TORRES","E")));
		Flux<Customer> customerf = customerm.flatMapMany(Flux::fromIterable);
		when(customerService.retrieveAll()).thenReturn(customerf);
		webTestClient.get().uri("/customer")
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
		.expectBodyList(Customer.class);
		*/
	}
	

}
