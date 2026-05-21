package com.client_microservice.api.domain.models;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

@ToString
public class Client {

	private Long id;
	private String name;
	private String lastName;
	private String documentType;
	private String documentNumber;
	private Integer age;
	private Boolean isActive;

	private List<Address> addresses; 

	public Client(Long id, String name, String lastName, String documentType, String documentNumber, Integer age,
			Boolean isActive, List<Address> addresses) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.age = age;
		this.isActive = isActive;
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		if (address != null) {
			this.addresses.add(address);
		}
	}

	public void deleteAddress(Long addressId) {
		this.addresses.removeIf(address -> address.getId() != null && address.getId().equals(addressId));
	}

	// Getters y Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
