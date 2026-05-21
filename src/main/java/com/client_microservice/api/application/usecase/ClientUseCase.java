package com.client_microservice.api.application.usecase;

import java.util.List;
import java.util.Optional;

import com.client_microservice.api.domain.api.ClientServicePort;
import com.client_microservice.api.domain.exception.NotFoundByIdException;
import com.client_microservice.api.domain.models.Client;
import com.client_microservice.api.domain.spi.ClientPersistencePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientUseCase implements ClientServicePort {

	private final ClientPersistencePort persistencePort;

	@Override
	public Client create(Client client) {

		Client newClient = new Client(null, client.getName(), client.getLastName(), client.getDocumentType(),
				client.getDocumentNumber(), client.getAge(), client.getIsActive(), client.getAddresses());

		return persistencePort.save(newClient);
	}

	@Override
	public Client update(Long id, Client client) {
		return persistencePort.findById(id).map(oldClient -> {

			oldClient.setName(client.getName());
			oldClient.setLastName(client.getLastName());
			oldClient.setDocumentType(client.getDocumentType());
			oldClient.setDocumentNumber(client.getDocumentNumber());
			oldClient.setAge(client.getAge());
			oldClient.setIsActive(client.getIsActive());
			oldClient.setAddresses(client.getAddresses());

			return persistencePort.save(oldClient);

		}).orElseThrow(() -> new NotFoundByIdException(id));
	}

	@Override
	public Optional<Client> getById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public List<Client> getAll() {
		return persistencePort.findAll();
	}

	@Override
	public void delete(Long id) {
		persistencePort.delete(id);
	}

}
