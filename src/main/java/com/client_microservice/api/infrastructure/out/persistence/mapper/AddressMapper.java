package com.client_microservice.api.infrastructure.out.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.client_microservice.api.domain.models.Address;
import com.client_microservice.api.infrastructure.out.persistence.entity.AddressEntity;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	Address toDomain(AddressEntity entity);

	List<Address> totoDomainDto(Iterable<AddressEntity> entities);

	AddressEntity toEntity(Address domain);

	List<AddressEntity> toEntity(Iterable<Address> domain);

}
