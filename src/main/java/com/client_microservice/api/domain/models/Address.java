package com.client_microservice.api.domain.models;

public class Address {

	private Long id;
	private String region;
	private String city;
	private String fullAddress;

	public Address(Long id, String region, String city, String fullAddress) {
		this.id = id;
		this.region = region;
		this.city = city;
		this.fullAddress = fullAddress;
	}

	// Getters y Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

}
