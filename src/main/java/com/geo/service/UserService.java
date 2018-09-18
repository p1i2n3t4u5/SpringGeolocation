package com.geo.service;

import java.util.List;

import com.geo.entities.User;

public interface UserService {

	User findById(long id);

	User findByLogin(String name);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);

}
