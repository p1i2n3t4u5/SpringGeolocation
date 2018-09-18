package com.geo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.entities.Role;
import com.geo.repository.RoleRepository;
import com.geo.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAllRoles() {
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

	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	public void deleteRoleById(long id) {
		roleRepository.deleteById(id);
	}

	public boolean isRoleExist(Role user) {
		return findByRoleName(user.getRoleName()) != null;
	}

	public void deleteAllRoles() {
		roleRepository.deleteAllInBatch();
	}

}
