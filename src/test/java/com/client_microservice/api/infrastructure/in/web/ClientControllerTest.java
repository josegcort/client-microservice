package com.client_microservice.api.infrastructure.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.client_microservice.api.domain.api.ClientServicePort;
import com.client_microservice.api.domain.models.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private ClientServicePort servicePort;

	private Client clientDomain;

	@BeforeEach
	void setUp() {
		clientDomain = new Client(1L, "Jose", "Gomez", "CC", "1002200", 25, true, new ArrayList<>());
	}

	@Test
	@DisplayName("POST /api/v1/clientes -> Debería retornar 201 Created y el cliente en formato JSON")
	void crearClienteEndToEnd() throws Exception {
		when(servicePort.create(any(Client.class))).thenReturn(clientDomain);

		mockMvc.perform(post("/api/v1/clients").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(clientDomain))).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Jose")).andExpect(jsonPath("$.documentNumber").value("1002200"));
	}

	@Test
	@DisplayName("GET /api/v1/clientes/{id} -> Debería retornar 200 OK si el cliente existe")
	void consultarClienteExistenteEndToEnd() throws Exception {
		when(servicePort.getById(1L)).thenReturn(Optional.of(clientDomain));

		mockMvc.perform(get("/api/v1/clients/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.documentNumber").value("1002200")).andExpect(jsonPath("$.name").value("Jose"));
	}

	@Test
	@DisplayName("GET /api/v1/clientes/{id} -> Debería retornar 404 Not Found si el cliente no existe")
	void consultarClienteInexistenteEndToEnd() throws Exception {
		// Arrange
		when(servicePort.getById(99L)).thenReturn(Optional.empty());

		// Act & Assert
		mockMvc.perform(get("/api/v1/clientes/99")).andExpect(status().isNotFound());
	}
}