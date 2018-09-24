package com.geo.service;

import java.util.List;

import com.geo.entities.Role;

public interface RoleService {

	Role findById(long id);

	Role findByRoleName(String name);

	Role save(Role user);

	Role update(Role user);

	void deleteById(long id);

	List<Role> findAll();
	
	boolean isExist(Role user);

	void deleteAll();

}
