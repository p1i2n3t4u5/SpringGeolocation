package com.geo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geo.entities.Navigation;
import com.geo.entities.Role;
import com.geo.entities.RoleNavigation;
import com.geo.repository.NavigationRepository;
import com.geo.repository.RoleNavigationRepository;
import com.geo.repository.RoleRepository;
import com.geo.service.RoleNavigationService;

@Service("roleNavigationService")
@Transactional
public class RoleNavigationServiceImpl implements RoleNavigationService {

	@Autowired
	RoleNavigationRepository roleNavigationRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	NavigationRepository navigationRepository;

	public List<RoleNavigation> findAll() {
		return roleNavigationRepository.findAll();
	}

	public RoleNavigation findById(long id) {
		Optional<RoleNavigation> roleNavigation = roleNavigationRepository.findById(id);
		if (roleNavigation.isPresent()) {
			return roleNavigation.get();
		}
		return null;
	}

	public RoleNavigation save(RoleNavigation roleNavigation) {
		
		Optional<Role> roleOptional=roleRepository.findById(roleNavigation.getRole().getId());
		if (roleOptional.isPresent()) {
			roleNavigation.setRole(roleOptional.get());
		}
		Optional<Navigation> navigationOptional=navigationRepository.findById(roleNavigation.getNavigation().getId());
		if (navigationOptional.isPresent()) {
			roleNavigation.setNavigation(navigationOptional.get());
		}
		return roleNavigationRepository.save(roleNavigation);
	}

	public RoleNavigation update(RoleNavigation roleNavigation) {
		return roleNavigationRepository.save(roleNavigation);
	}

	public void deleteById(long id) {
		roleNavigationRepository.deleteById(id);
	}

	public void deleteAll() {
		roleNavigationRepository.deleteAllInBatch();
	}

	@Override
	public List<RoleNavigation> findAllByRoleId(Long roleId) {
		return roleNavigationRepository.findAllByRoleId(roleId);
	}

	@Override
	public boolean isExist(RoleNavigation roleNavigation) {
		return roleNavigationRepository.findByRoleAndNavigation(roleNavigation.getRole().getId(),
				roleNavigation.getNavigation().getId()) != null ? true : false;
	}

}
