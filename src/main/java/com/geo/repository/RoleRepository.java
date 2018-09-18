package com.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRoleName(String role);


}
