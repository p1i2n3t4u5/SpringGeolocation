package com.geo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.geo.entities.Role;
import com.geo.entities.User;


public class CustomUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Set<Role> userRoles;

	public CustomUserDetails(User user, Set<Role> userRoles) {
		super(user);
		this.userRoles=userRoles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roles=new ArrayList<>();
		for (Role role : userRoles) {
			roles.add(role.getRoleName());
		}
		
		String rolesStr=StringUtils.collectionToCommaDelimitedString(roles);
	    return AuthorityUtils.commaSeparatedStringToAuthorityList(rolesStr);
	}

	@Override
	public String getUsername() {
		return super.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}