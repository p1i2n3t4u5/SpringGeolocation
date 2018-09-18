package com.geo;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.geo.entities.Role;
import com.geo.entities.User;
import com.geo.service.RoleService;
import com.geo.service.UserService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringGeolocationApplication implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringGeolocationApplication.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.debug("hello");
		System.err.println("run() started");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Role adminRole = roleService.findByRoleName("admin");
		Role role = null;
		Role role2 = null;
		Role role3 = null;

		if (adminRole == null) {
			role = new Role();
			role.setRoleName("admin");
			role = roleService.saveRole(role);
		} else {
			System.err.println("role already exist " + adminRole);
		}

		Role userRole = roleService.findByRoleName("user");

		if (userRole == null) {
			role2 = new Role();
			role2.setRoleName("user");
			role2 = roleService.saveRole(role2);
		} else {
			System.err.println("role2 already exist " + userRole);
		}

		Role superuserRole = roleService.findByRoleName("superuser");

		if (superuserRole == null) {
			role3 = new Role();
			role3.setRoleName("superuser");
			role3 = roleService.saveRole(role3);
		} else {
			System.err.println("role3 already exist " + superuserRole);
		}

		User user = userService.findByLogin("pintu");
		if (user == null) {
			user = new User();
			user.setAcive(true);
			user.setEmail("niranjanpanigrahi2009@gmail.com");
			user.setFirstName("Niranjan");
			user.setLastName("Panigrahi");
			user.setLogin("pintu");
			user.setPassword(encoder.encode("pintu12345"));
			user.setPhone("8951560216");
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			roles.add(role2);
			roles.add(role3);
			user.setRoles(roles);
			user = userService.saveUser(user);
		} else {
			System.err.println("user1 already exist " + user);
		}

		User user2 = userService.findByLogin("satya");
		if (user2 == null) {
			user2 = new User();
			user2.setAcive(true);
			user2.setEmail("satyamishra@gmail.com");
			user2.setFirstName("Satya");
			user2.setLastName("Mishra");
			user2.setLogin("satya");
			user2.setPassword(encoder.encode("satya12345"));
			user2.setPhone("9999999999");
			Set<Role> roles2 = new HashSet<>();
			roles2.add(role);
			roles2.add(role2);
			roles2.add(role3);
			user2.setRoles(roles2);
			user2 = userService.saveUser(user2);
		} else {
			System.err.println("user2 already exist " + user2);
		}

		System.err.println("run() ended");
	}

}
