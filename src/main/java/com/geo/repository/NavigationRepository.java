package com.geo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.geo.entities.Navigation;

public interface NavigationRepository extends JpaRepository<Navigation, Long> {

	Navigation findByNameToken(String nameToken);

	Navigation findByScreenLabel(String screenLabel);

	Navigation findByScreenType(String screenType);

	List<Navigation> findAllByParent(long parentId);

	List<Navigation> findAllByRoles(@Param("roleIds") List<Long> roleIds);
	

}
