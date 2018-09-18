package com.geo.service;

import java.util.List;

import com.geo.entities.Role;

public interface RoleService {

	Role findById(long id);

	Role findByRoleName(String name);

	Role saveRole(Role user);

	Role updateRole(Role user);

	void deleteRoleById(long id);

	List<Role> findAllRoles();

	void deleteAllRoles();

	public boolean isRoleExist(Role user);

}
