package com.geo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geo.entities.Navigation;
import com.geo.entities.Role;
import com.geo.entities.User;
import com.geo.repository.NavigationRepository;
import com.geo.repository.RoleRepository;
import com.geo.repository.UserRepository;
import com.geo.service.NavigationService;

@Service("navigationService")
@Transactional
public class NavigationServiceImpl implements NavigationService {

	@Autowired
	NavigationRepository navigationRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	public List<Navigation> findAllNavigations() {
		return navigationRepository.findAll();
	}

	public Navigation findById(long id) {
		Optional<Navigation> navigation = navigationRepository.findById(id);
		if (navigation.isPresent()) {
			return navigation.get();
		}
		return null;
	}

	public Navigation save(Navigation navigation) {
		if (navigation.getParent() != null) {
			navigation.setParent(navigationRepository.getOne(navigation.getParent().getId()));
		}
		return navigationRepository.save(navigation);
	}

	public Navigation update(Navigation navigation) {
		return navigationRepository.save(navigation);
	}

	public void deleteById(long id) {
		navigationRepository.deleteById(id);
	}

	public void deleteAll() {
		navigationRepository.deleteAllInBatch();
	}

	@Override
	public Navigation findByNameToken(String nameToken) {
		return navigationRepository.findByNameToken(nameToken);
	}

	@Override
	public Navigation findByScreenLabel(String screenLabel) {
		return navigationRepository.findByScreenLabel(screenLabel);
	}

	@Override
	public Navigation findByScreenType(String screenType) {
		return navigationRepository.findByScreenType(screenType);
	}

	@Override
	public List<Navigation> findAll() {
		return navigationRepository.findAll();
	}

	@Override
	public List<Navigation> findAllByParent(long parentId) {
		return navigationRepository.findAllByParent(parentId);
	}

	@Override
	public List<Navigation> findAllByRoles(List<Long> roleIds) {
		return navigationRepository.findAllByRoles(roleIds);
	}

	@Override
	public boolean isExist(Navigation navigation) {
		return findByNameToken(navigation.getNameToken()) != null;
	}

	@Override
	public List<Navigation> findAllByUser(Long userId) {
		List<Long> roleIds = new ArrayList<>();
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			Set<Role> roles = optional.get().getRoles();
			for (Role role : roles) {
				roleIds.add(role.getId());
			}
		}
		List<Navigation> navigations = navigationRepository.findAllByRoles(roleIds);
		return navigations;
	}

}
