package com.geo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.geo.entities.Address;
import com.geo.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressRestController {

	@Autowired
	AddressService addressService;

	// -------------------Retrieve All
	// Addresss--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Address>> listAllAddress() {
		List<Address> address = addressService.findAll();
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Address--------------------------------------------------------

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Address> getAddress(@PathVariable("id") long id) {
		System.out.println("Fetching Address with id " + id);
		Address address = addressService.findById(id);
		if (address == null) {
			System.out.println("Address with id " + id + " not found");
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}

	// -------------------Create a
	// Address--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createAddress(@RequestBody Address address, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Address " + address.toString());
		addressService.save(address);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/findByCountry", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Address>> listAllAddressByCountry(@RequestParam("country") String country) {
		List<Address> address = addressService.findByCountry(country);
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/findByDistrict", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Address>> listAllAddressByDistrict(@RequestParam("district") String district) {
		List<Address> address = addressService.findByDistrict(district);
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/findByState", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Address>> listAllAddressByState(@RequestParam("state") String state) {
		List<Address> address = addressService.findByState(state);
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

}