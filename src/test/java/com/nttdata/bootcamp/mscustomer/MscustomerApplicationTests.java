package com.nttdata.bootcamp.mscustomer;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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

import  static  org.mockito.ArgumentMatchers.any;
import  static org.mockito.BDDMockito.given;
import  static  org.mockito.Mockito.when;

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
	public void crearTest() {
		Mono<Customer> customer = Mono.just(new Customer("4444", "4565456", "JUAN", "TORRES","E",null));
			when(customerService.insertCustomer(customer)).thenReturn(customer);
			webTestClient.post().uri("/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(customer), Customer.class)
			.exchange()
			.expectStatus().isCreated();
	}
	

}
