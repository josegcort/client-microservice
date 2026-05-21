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

import com.client_microservice.api.domain.api.ClientServicePort;
import com.client_microservice.api.domain.models.Client;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

	private final ClientServicePort servicePort;

	@PostMapping
	public ResponseEntity<Client> create(@RequestBody Client client) {
		Client client2 = servicePort.create(client);
		return new ResponseEntity<>(client2, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
		return ResponseEntity.ok(servicePort.update(id, client));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getById(@PathVariable Long id) {
		return servicePort.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<Client>> getAll() {
		return ResponseEntity.ok(servicePort.getAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		servicePort.delete(id);
		return ResponseEntity.ok("Cliente eliminado");
	}

}
