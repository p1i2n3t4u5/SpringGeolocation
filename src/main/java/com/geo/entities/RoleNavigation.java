package com.geo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * The persistent class for the actionpart database table.
 * 
 */

@NamedQueries({
		@NamedQuery(name = RoleNavigation.FIND_ALL_BY_ROLE_ID, query = "SELECT rn  FROM RoleNavigation rn Where rn.role.id = :roleId"),
		@NamedQuery(name = RoleNavigation.FIND_ALL_BY_ROLE_AND_NAVIGATION, query = "SELECT rn  FROM RoleNavigation rn Where rn.role.id = :roleId  AND rn.navigation.id=:navigationId ") })

@Entity(name = "RoleNavigation")
@Table(name = "role_navigation")
public class RoleNavigation implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL_BY_ROLE_ID = "RoleNavigation.findAllByRoleId";
	public static final String FIND_ALL_BY_ROLE_AND_NAVIGATION = "RoleNavigation.findByRoleAndNavigation";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "navigation_id", nullable = false)
	private Navigation navigation;

	@Version
	@Column(name = "version")
	private long version;

	public RoleNavigation() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "RoleNavigation [id=" + id + ", role=" + role + ", navigation=" + navigation + ", version=" + version
				+ "]";
	}

}