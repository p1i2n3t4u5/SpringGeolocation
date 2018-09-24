package com.geo.service;

import java.util.List;

import com.geo.entities.RoleNavigation;

public interface RoleNavigationService {

	RoleNavigation findById(long id);

	RoleNavigation save(RoleNavigation navigation);

	RoleNavigation update(RoleNavigation navigation);

	void deleteById(long id);

	List<RoleNavigation> findAll();

	List<RoleNavigation> findAllByRoleId(Long roleId);

	void deleteAll();

	boolean isExist(RoleNavigation roleNavigation);

}
