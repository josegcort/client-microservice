package com.client_microservice.api.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.client_microservice.api.application.usecase.ClientUseCase;
import com.client_microservice.api.domain.exception.NotFoundByIdException;
import com.client_microservice.api.domain.models.Address;
import com.client_microservice.api.domain.models.Client;
import com.client_microservice.api.domain.spi.ClientPersistencePort;

@ExtendWith(MockitoExtension.class)
class ClienteUseCaseTest {

	@Mock
	private ClientPersistencePort persistencePort;

	@InjectMocks
	private ClientUseCase useCase;

	private Client clientMock;
	private Client clientMockUpdate;

	@BeforeEach
	void setUp() {
		clientMock = new Client(1L, "Jose", "Gomez", "CC", "1002200", 25, true, new ArrayList<>());
		clientMock.addAddress(new Address(1L, "Quindio", "Armenia", "Calle 10"));

		clientMockUpdate = new Client(1L, "Jose Manuel", "Diaz", "CC", "1002200", 25, true, new ArrayList<>());
		clientMockUpdate.addAddress(new Address(2L, "Quindio", "Armenia", "Cra. 10"));
		clientMockUpdate.addAddress(new Address(3L, "Quindio", "Calarca", "Calle 9na"));

	}

	@Test
	@DisplayName("Debería crear un cliente exitosamente")
	void crearClienteExitoso() {
		// Arrange
		when(persistencePort.save(any(Client.class))).thenReturn(clientMock);

		// Act
		Client result = useCase.create(clientMock);

		// Assert
		assertNotNull(result);
		assertEquals("Jose", result.getName());
		assertEquals(1, result.getAddresses().size());
		verify(persistencePort, times(1)).save(any(Client.class));
	}

	@Test
	@DisplayName("Debería retornar un cliente cuando se consulta por ID existente")
	void consultarClienteExistente() {
		// Arrange
		when(persistencePort.findById(1L)).thenReturn(Optional.of(clientMock));

		// Act
		Optional<Client> result = useCase.getById(1L);

		// Assert
		assertTrue(result.isPresent());
		assertEquals("1002200", result.get().getDocumentNumber());
	}

	@Test
	@DisplayName("Debería lanzar una excepción cuando se intenta modificar un cliente que no existe")
	void modificarClienteInexistenteLanzaExcepcion() {
		// Arrange
		when(persistencePort.findById(99L)).thenReturn(Optional.empty());

		// Act & Assert
		NotFoundByIdException exception = assertThrows(NotFoundByIdException.class, () -> {
			useCase.update(99L, clientMock);
		});

		assertEquals("No se encontro el registro con el id: 99", exception.getMessage());
		verify(persistencePort, never()).save(any());
	}

	@Test
	@DisplayName("Debería modificar un cliente exitosamente")
	void modificarClienteExistente() {

		// Arrange
		when(persistencePort.findById(1L)).thenReturn(Optional.of(clientMock));

		when(persistencePort.save(any(Client.class))).thenReturn(clientMockUpdate);

		// Act
		Client result = useCase.update(1L, clientMockUpdate);

		// Assert
		assertNotNull(result);

		assertEquals("Jose Manuel", result.getName());
		assertEquals("Diaz", result.getLastName());

		assertEquals(2, result.getAddresses().size());

		verify(persistencePort, times(1)).findById(1L);
		verify(persistencePort, times(1)).save(any(Client.class));
	}

}