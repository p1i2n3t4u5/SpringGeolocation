package com.geo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.entities.Role;
import com.geo.exception.DuplicateEntryException;
import com.geo.repository.RoleRepository;
import com.geo.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findById(long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (role.isPresent()) {
			return role.get();
		}
		return null;
	}

	public Role findByRoleName(String name) {
		return roleRepository.findByRoleName(name);
	}

	public Role save(Role role) {

		Role role2 = roleRepository.findByRoleName(role.getRoleName());
		if (role2 != null) {
			throw new DuplicateEntryException("role with role name =" + role.getRoleName() + " already exist");
		}
		return roleRepository.save(role);
	}

	public Role update(Role role) {
		return roleRepository.save(role);
	}

	public void deleteById(long id) {
		roleRepository.deleteById(id);
	}

	public boolean isExist(Role user) {
		return findByRoleName(user.getRoleName()) != null;
	}

	public void deleteAll() {
		roleRepository.deleteAllInBatch();
	}

}
