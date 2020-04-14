package com.geo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.geo.entities.Address;
import com.geo.exception.NotFoundException;
import com.geo.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressRestController {

	@Autowired
	AddressService addressService;

	@Autowired
	MessageSource messageSource;

	// -------------------Retrieve All
	// Addresss--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)

	public ResponseEntity<List<Address>> listAllAddress() {
		List<Address> address = addressService.findAll();
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

//	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	
//	public ResponseEntity<Address> getAddress(@PathVariable("id") long id) {
//		System.out.println("Fetching Address with id " + id);
//		Address address = addressService.findById(id);
//		if (address == null) {
//			System.out.println("Address with id " + id + " not found");
//			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Address>(address, HttpStatus.OK);
//	}

//	@GetMapping(path = "{id}")
//	public ResponseEntity<Address> getAddress(@PathVariable("id") long id) {
//		System.out.println("Fetching Address with id " + id);
//		Address address = addressService.findById(id);
//		if (address == null) {
//			System.out.println("Address with id " + id + " not found");
//			throw new NotFoundException("Address Not Found with id:" + id);
//		}
//		return new ResponseEntity<Address>(address, HttpStatus.OK);
//	}

	// implementing with HATEOAS

	@GetMapping(path = "{id}")
	public Resource<Address> getAddress(@PathVariable("id") long id) {
		System.out.println("Fetching Address with id " + id);
		Address address = addressService.findById(id);
		if (address == null) {
			System.out.println("Address with id " + id + " not found");
			throw new NotFoundException("Address Not Found with id:" + id);
		}

		// HATEOAS ControllerLinkBuilder
		Resource<Address> resource = new Resource<Address>(address);
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).listAllAddress());
		resource.add(link.withRel("all-addresses"));

		return resource;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Void> createAddress(@Valid @RequestBody Address address, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Address " + address.toString());
		addressService.save(address);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri());
//		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		// new approach
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/findByCountry/{country}", method = RequestMethod.GET)

	public ResponseEntity<List<Address>> listAllAddressByCountry(@PathVariable("country") String country) {
		List<Address> address = addressService.findByCountry(country);
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/findByDistrict/{district}", method = RequestMethod.GET)

	public ResponseEntity<List<Address>> listAllAddressByDistrict(@PathVariable("district") String district) {
		List<Address> address = addressService.findByDistrict(district);
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/findByState/{state}", method = RequestMethod.GET)

	public ResponseEntity<List<Address>> listAllAddressByState(@PathVariable("state") String state) {
		List<Address> address = addressService.findByState(state);
		if (address.isEmpty()) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/paged", method = RequestMethod.GET)

	public ResponseEntity<Page<Address>> listAllAddressPaged(@RequestParam("page") int page,
			@RequestParam("size") int size) {
		Page<Address> address = addressService.findAllPaged(PageRequest.of(page, size));
		if (address.getSize() == 0) {
			return new ResponseEntity<Page<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<Page<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = "/slice", method = RequestMethod.GET)

	public ResponseEntity<Slice<Address>> listAllAddressSliced(@RequestParam("page") int page,
			@RequestParam("size") int size) {
		Slice<Address> slice = addressService.findAllSliced(PageRequest.of(page, size,Sort.by(Direction.ASC,"id")));
		if (slice.getSize() == 0) {
			return new ResponseEntity<Slice<Address>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<Slice<Address>>(slice, HttpStatus.OK);
	}

	@RequestMapping(value = "/test/urlencodedparams", method = RequestMethod.POST)

	public ResponseEntity<Void> postUrlEncodedParams(@RequestBody Address address) {

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	// technique for accessing url encodded form params
	// http://localhost:8080/SpringGeolocation/address/test/urlencodedparams
	// param is data

	@RequestMapping(value = "/test/urlencodedparams", method = RequestMethod.POST, headers = {
			"content-type=application/x-www-form-urlencoded" })
	public ResponseEntity<Void> waboxapp(WebRequest request) {
		// then use something like:
		System.out.println("request payload: " + request.getParameter("data"));
		System.out.println("request payload: " + request.getParameter("data2"));

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/test/urlencodedparams2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Void> createRole(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println("parameterMap:" + parameterMap);

		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			System.out.println(entry.getKey() + "     " + entry.getValue()[0]);
		}
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "hello-world-i18n")
	public String goodMorningI18n(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	//alternative approach
	//http://localhost:8080/SpringGeolocation/address/hello-world-i18n2  Accept-Language in header 
	@GetMapping(path = "hello-world-i18n2")
	public String goodMorningI18n2() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}