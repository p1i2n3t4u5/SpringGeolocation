package com.geo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geo.entities.Role;
import com.geo.entities.User;
import com.geo.repository.RoleRepository;
import com.geo.repository.UserRepository;
import com.geo.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	public User findByLogin(String name) {
		return userRepository.findByLogin(name);
	}

	public User save(User user) {
		Set<Role> roles = user.getRoles();
		Set<Role> newRoles = new HashSet<>();
		if (roles != null) {
			for (Role role : roles) {
				Optional<Role> roleOption=roleRepository.findById(role.getId());
				if (roleOption.isPresent()) {
					newRoles.add(roleOption.get());
				}
			}
		}
		
		user.setRoles(newRoles);
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	public boolean isExist(User user) {
		return findByLogin(user.getLogin()) != null;
	}

	public void deleteAll() {
		userRepository.deleteAllInBatch();
	}

}
