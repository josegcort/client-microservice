package com.client_microservice.api.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.client_microservice.api.application.usecase.AddressUseCase;
import com.client_microservice.api.application.usecase.ClientUseCase;
import com.client_microservice.api.domain.api.AddressServicePort;
import com.client_microservice.api.domain.api.ClientServicePort;
import com.client_microservice.api.domain.spi.AddressPersistencePort;
import com.client_microservice.api.domain.spi.ClientPersistencePort;

@Configuration
public class BeanConfiguration {

	@Bean
	public ClientServicePort clientServicePort(ClientPersistencePort clientPersistencePort) {
		return new ClientUseCase(clientPersistencePort);
	}

	@Bean
	public AddressServicePort addressServicePort(AddressPersistencePort addressPersistencePort) {
		return new AddressUseCase(addressPersistencePort);
	}
}
