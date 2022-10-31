package com.nttdata.bootcamp.mscustomer.interfaces.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcamp.mscustomer.application.handler.CustomerHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CustomerRouter {
	
	@Bean
	public RouterFunction<ServerResponse> customerRouteFunc(CustomerHandler customerHandler){
		return RouterFunctions.route(POST("/create").and(accept(MediaType.APPLICATION_JSON)),customerHandler::create)
				.andRoute(GET("/get/all").and(accept(MediaType.APPLICATION_JSON)),customerHandler::getAll);
	}
}
