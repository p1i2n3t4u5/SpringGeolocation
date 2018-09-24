package com.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByLogin(String login);
	
}


