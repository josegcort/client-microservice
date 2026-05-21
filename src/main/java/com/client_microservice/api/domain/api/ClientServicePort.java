package com.client_microservice.api.domain.api;

import java.util.List;
import java.util.Optional;

import com.client_microservice.api.domain.models.Client;

public interface ClientServicePort {

	Client create(Client client);

	Client update(Long id, Client client);

	Optional<Client> getById(Long id);

	List<Client> getAll();

	void delete(Long id);

}
