package com.geo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.geo.entities.RoleNavigation;

@Repository
public interface RoleNavigationRepository extends JpaRepository<RoleNavigation, Long> {

	List<RoleNavigation> findAllByRoleId(@Param("roleId") Long roleId);

	RoleNavigation findByRoleAndNavigation(@Param("roleId") long roleId,@Param("navigationId") long navigationId);

}
