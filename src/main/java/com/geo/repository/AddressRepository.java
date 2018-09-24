package com.geo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> { 
	
	List<Address> findByCountry(String country);
	
	List<Address> findByState(String state); 
	
	List<Address> findByDistrict(String district); 
	

}
