package com.nttdata.bootcamp.mscustomer.application;

import com.nttdata.bootcamp.mscustomer.model.Customer;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customer")
@RefreshScope
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Value("${message.demo}")
	private String demoString;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Customer> crear(@RequestBody Customer customer) {
		log.info(demoString);
		return customerService.insertCustomer(Mono.just(customer));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Customer> retrieveAll() {
		return customerService.retrieveAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Customer>> findById(@PathVariable String id) {
		log.info(id);
		return customerService.findById(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id) {
		return customerService.findById(id)
				.flatMap(customerService::delete)
				.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Customer>> editar(@RequestBody Customer customer, @PathVariable String id) {
		return customerService.findById(id).flatMap(p -> {
			p.setName(customer.getName());
			p.setLastname(customer.getLastname());
			p.setDocumentNumber(customer.getDocumentNumber());
			return customerService.insertCustomer(Mono.just(p));
		}).map(p -> ResponseEntity.created(URI.create("/customer/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(p)).defaultIfEmpty(ResponseEntity.notFound().build());

	}

}
