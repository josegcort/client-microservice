package com.client_microservice.api.domain.spi;

import java.util.List;
import java.util.Optional;

import com.client_microservice.api.domain.models.Address;

public interface AddressPersistencePort {
	Address save(Address address);

	Optional<Address> findById(Long id);

	List<Address> findAll();

	void delete(Long id);
}
