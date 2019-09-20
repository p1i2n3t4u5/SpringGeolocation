package com.geo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.geo.entities.Address;

public interface AddressService {

	Address findById(long id);

	List<Address> findByCountry(String country);

	List<Address> findByState(String state);

	List<Address> findByDistrict(String district);

	Address save(Address user);

	Address update(Address user);

	void deleteById(long id);

	List<Address> findAll();

	Page<Address> findAllPaged(Pageable pageable);

	Slice<Address> findAllSliced(Pageable pageable,Sort sort);

}
