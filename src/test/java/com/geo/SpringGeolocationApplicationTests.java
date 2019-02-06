package com.geo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.geo.entities.User;
import com.geo.service.AddressService;
import com.geo.service.NavigationService;
import com.geo.service.RoleNavigationService;
import com.geo.service.RoleService;
import com.geo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringGeolocationApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	AddressService addressService;

	@Autowired
	NavigationService navigationService;

	@Autowired
	RoleNavigationService roleNavigationService;

	@Test
	public void contextLoads() {
		List<User> users = userService.findAll();
		System.err.println(users);
		assertNotNull(users);
		assertTrue(users.size() == 4);
	}

}
