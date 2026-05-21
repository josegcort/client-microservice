package com.client_microservice.api.infrastructure.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.client_microservice.api.domain.models.Client;
import com.client_microservice.api.infrastructure.out.persistence.entity.ClientEntity;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface ClientMapper {

	Client toDomain(ClientEntity entity);

	List<Client> totoDomainDto(Iterable<ClientEntity> entities);

	ClientEntity toEntity(Client domain);

	List<ClientEntity> toEntity(Iterable<Client> domain);

}
