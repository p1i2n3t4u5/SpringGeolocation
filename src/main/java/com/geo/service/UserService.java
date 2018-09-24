package com.geo.service;

import java.util.List;

import com.geo.entities.User;

public interface UserService {

	User findById(long id);

	User findByLogin(String name);

	User save(User user);

	User update(User user);

	void deleteById(long id);

	List<User> findAll();

	void deleteAll();

	public boolean isExist(User user);

}
