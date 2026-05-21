package com.client_microservice.api.domain.api;

import java.util.List;
import java.util.Optional;

import com.client_microservice.api.domain.models.Address;

public interface AddressServicePort {

	Address create(Address address);

	Address update(Long id, Address address);

	Optional<Address> getById(Long id);

	List<Address> getAll();

	void delete(Long id);

}
