package com.client_microservice.api.infrastructure.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client_microservice.api.domain.api.AddressServicePort;
import com.client_microservice.api.domain.models.Address;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

	private final AddressServicePort servicePort;

	@PostMapping
	public ResponseEntity<Address> create(@RequestBody Address address) {
		Address address2 = servicePort.create(address);

		return new ResponseEntity<>(address2, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {
		return ResponseEntity.ok(servicePort.update(id, address));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> getById(@PathVariable Long id) {
		return servicePort.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<Address>> getAll() {
		List<Address> addresses = servicePort.getAll();
		if (addresses.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(addresses);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		servicePort.delete(id);
		return ResponseEntity.ok("Cliente eliminado");
	}

}
