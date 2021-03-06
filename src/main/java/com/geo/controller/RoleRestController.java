package com.geo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.geo.entities.Role;
import com.geo.service.RoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleRestController {

	@Autowired
	RoleService roleService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Roles--------------------------------------------------------

	// @RequestMapping(value = "", method = RequestMethod.GET)
	// @ResponseBody
	@GetMapping
	public ResponseEntity<List<Role>> listAllRoles() {
		List<Role> roles = roleService.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Role--------------------------------------------------------

	// @RequestMapping(value = "{id}", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	@GetMapping("{id}")
	public ResponseEntity<Role> getRole(@PathVariable("id") long id) {
		System.out.println("Fetching Role with id " + id);
		Role role = roleService.findById(id);
		if (role == null) {
			System.out.println("Role with id " + id + " not found");
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	// -------------------Create a
	// Role--------------------------------------------------------

	// @RequestMapping(value = "", method = RequestMethod.POST)
	// @ResponseBody
	@PostMapping
	public ResponseEntity<Void> createRole(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Role " + role.getRoleName());

		if (roleService.isExist(role)) {
			System.out.println("A Role with name " + role.getRoleName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		roleService.save(role);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/role/{id}").buildAndExpand(role.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}