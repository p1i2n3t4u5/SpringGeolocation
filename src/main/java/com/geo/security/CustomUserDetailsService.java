package com.geo.security;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.geo.entities.Role;
import com.geo.entities.User;
import com.geo.repository.UserRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		System.err.println(logger);
		logger.trace("A TRACE Message inside CustomUserDetailsService ");
		logger.debug("A DEBUG Message inside CustomUserDetailsService");
		logger.info("An INFO Message inside CustomUserDetailsService");
		logger.warn("A WARN Message inside CustomUserDetailsService");
		logger.error("An ERROR Message inside CustomUserDetailsService");
		User user = userRepository.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("No user present with username: " + login);
		} else {
			Set<Role> roles = user.getRoles();
			return new CustomUserDetails(user, roles);
		}
	}

}