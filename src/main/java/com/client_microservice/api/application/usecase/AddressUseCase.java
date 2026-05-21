package com.client_microservice.api.application.usecase;

import java.util.List;
import java.util.Optional;

import com.client_microservice.api.domain.api.AddressServicePort;
import com.client_microservice.api.domain.exception.NotFoundByIdException;
import com.client_microservice.api.domain.models.Address;
import com.client_microservice.api.domain.spi.AddressPersistencePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressUseCase implements AddressServicePort {

	private final AddressPersistencePort persistencePort;

	@Override
	public Address create(Address address) {

		Address newAddress = new Address(null, address.getRegion(), address.getCity(), address.getFullAddress());

		return persistencePort.save(newAddress);
	}

	@Override
	public Address update(Long id, Address address) {
		return persistencePort.findById(id).map(oldAddress -> {

			oldAddress.setRegion(address.getRegion());
			oldAddress.setCity(address.getCity());
			oldAddress.setFullAddress(address.getFullAddress());

			return persistencePort.save(oldAddress);

		}).orElseThrow(() -> new NotFoundByIdException(id));
	}

	@Override
	public Optional<Address> getById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public List<Address> getAll() {
		return persistencePort.findAll();
	}

	@Override
	public void delete(Long id) {
		persistencePort.delete(id);
	}

}
