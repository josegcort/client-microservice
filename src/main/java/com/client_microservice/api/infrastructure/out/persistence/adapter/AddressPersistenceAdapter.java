package com.client_microservice.api.infrastructure.out.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.client_microservice.api.domain.models.Address;
import com.client_microservice.api.domain.spi.AddressPersistencePort;
import com.client_microservice.api.infrastructure.out.persistence.entity.AddressEntity;
import com.client_microservice.api.infrastructure.out.persistence.mapper.AddressMapper;
import com.client_microservice.api.infrastructure.out.persistence.repository.AddressRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AddressPersistenceAdapter implements AddressPersistencePort {

	private final AddressRepository repository;
	private final AddressMapper mapper;

	@Override
	public Address save(Address address) {

		AddressEntity savedEntity = repository.save(mapper.toEntity(address));

		return mapper.toDomain(savedEntity);
	}

	@Override
	public Optional<Address> findById(Long id) {
		return repository.findById(id).map(mapper::toDomain);
	}

	@Override
	public List<Address> findAll() {
		return repository.findAll().stream().map(mapper::toDomain).toList();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
