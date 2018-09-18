package com.geo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "role_name")
	private String roleName;

//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//	@JsonBackReference
//	public Set<User> users;

	@Version
	private Long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", version=" + version + "]";
	}

}
