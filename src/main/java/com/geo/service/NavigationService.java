package com.geo.service;

import java.util.List;

import com.geo.entities.Navigation;

public interface NavigationService {

	Navigation findById(long id);

	Navigation findByNameToken(String nameToken);

	Navigation findByScreenLabel(String screenLabel);

	Navigation findByScreenType(String screenType);

	Navigation save(Navigation navigation);

	Navigation update(Navigation navigation);

	void deleteById(long id);

	List<Navigation> findAll();

	List<Navigation> findAllByParent(long parentId);

	List<Navigation> findAllByRoles(List<Long> roleIds);
	
	List<Navigation> findAllByUser(Long userId);

	void deleteAll();

	boolean isExist(Navigation navigation);

}
