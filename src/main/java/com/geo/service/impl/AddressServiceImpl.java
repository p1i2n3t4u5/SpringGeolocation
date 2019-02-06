package com.geo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geo.entities.Address;
import com.geo.repository.AddressRepository;
import com.geo.repository.RoleRepository;
import com.geo.service.AddressService;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	RoleRepository roleRepository;

	public List<Address> findAllAddresss() {
		return addressRepository.findAll();
	}

	@Cacheable(value="Address",key="#id")
	public Address findById(long id) {
		Optional<Address> address = addressRepository.findById(id);
		if (address.isPresent()) {
			return address.get();
		}
		return null;
	}

	
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@CachePut(value="Address",key = "#address.id" , unless="#result==null")
	public Address update(Address address) {
		return addressRepository.save(address);
	}

	@CacheEvict(value="Address",key="#id")
	public void deleteById(long id) {
		addressRepository.deleteById(id);
	}

	public void deleteAllAddresss() {
		addressRepository.deleteAllInBatch();
	}

	@Override
	public List<Address> findByCountry(String country) {
		return addressRepository.findByCountry(country);
	}

	@Override
	public List<Address> findByState(String state) {
		return addressRepository.findByState(state);
	}

	@Override
	public List<Address> findByDistrict(String district) {
		return addressRepository.findByDistrict(district);
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Page<Address> findAllPaged(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public Slice<Address> findAllSliced(Pageable pageable) {
		return addressRepository.findAllSliced(pageable);
	}

}
