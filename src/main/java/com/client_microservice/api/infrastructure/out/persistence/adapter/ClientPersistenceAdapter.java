package com.client_microservice.api.infrastructure.out.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.client_microservice.api.domain.models.Client;
import com.client_microservice.api.domain.spi.ClientPersistencePort;
import com.client_microservice.api.infrastructure.out.persistence.entity.ClientEntity;
import com.client_microservice.api.infrastructure.out.persistence.mapper.ClientMapper;
import com.client_microservice.api.infrastructure.out.persistence.repository.ClientRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientPersistenceAdapter implements ClientPersistencePort {

	private final ClientRepository repository;
	private final ClientMapper mapper;

	@Override
	public Client save(Client client) {

		ClientEntity savedEntity = repository.save(mapper.toEntity(client));

		return mapper.toDomain(savedEntity);
	}

	@Override
	public Optional<Client> findById(Long id) {
		return repository.findById(id).map(mapper::toDomain);
	}

	@Override
	public List<Client> findAll() {
		return repository.findAll().stream().map(mapper::toDomain).toList();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
