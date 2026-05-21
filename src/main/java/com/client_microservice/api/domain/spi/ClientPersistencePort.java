package com.client_microservice.api.domain.spi;

import java.util.List;
import java.util.Optional;

import com.client_microservice.api.domain.models.Client;

public interface ClientPersistencePort {
	Client save(Client client);

	Optional<Client> findById(Long id);

	List<Client> findAll();

	void delete(Long id);
}
