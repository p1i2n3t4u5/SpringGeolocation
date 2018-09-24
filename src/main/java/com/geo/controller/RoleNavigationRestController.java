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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.geo.entities.RoleNavigation;
import com.geo.service.RoleNavigationService;

@RestController
@RequestMapping(value = "/roleNavigation")
public class RoleNavigationRestController {

	@Autowired
	RoleNavigationService roleNavigationService; // Service which will do all data
	// retrieval/manipulation work

	// -------------------Retrieve All
	// RoleNavigations--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RoleNavigation>> listAllRoleNavigations() {
		List<RoleNavigation> roleNavigations = roleNavigationService.findAll();
		if (roleNavigations.isEmpty()) {
			return new ResponseEntity<List<RoleNavigation>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<RoleNavigation>>(roleNavigations, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// RoleNavigation--------------------------------------------------------

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<RoleNavigation> getRoleNavigation(@PathVariable("id") long id) {
		System.out.println("Fetching RoleNavigation with id " + id);
		RoleNavigation roleNavigation = roleNavigationService.findById(id);
		if (roleNavigation == null) {
			System.out.println("RoleNavigation with id " + id + " not found");
			return new ResponseEntity<RoleNavigation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RoleNavigation>(roleNavigation, HttpStatus.OK);
	}

	// -------------------Create a
	// RoleNavigation--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createRoleNavigation(@RequestBody RoleNavigation roleNavigation,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating RoleNavigation " + roleNavigation.toString());

		if (roleNavigationService.isExist(roleNavigation)) {
			System.out.println("A RoleNavigation with name " + roleNavigation.getRole().getRoleName() + " "
					+ roleNavigation.getNavigation().getNameToken() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		roleNavigationService.save(roleNavigation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/roleNavigation/{id}").buildAndExpand(roleNavigation.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}