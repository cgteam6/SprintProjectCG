package com.cg.oam.service;

import java.util.List;

import com.cg.oam.entities.User;
import com.cg.oam.exception.UserNotFoundException;

public interface UserService {
	User addUser(User user) throws UserNotFoundException;

	User updateUser(User user, int id) throws UserNotFoundException;

	User removeUser(int id) throws UserNotFoundException;

	List<User> showAllUsers()throws UserNotFoundException;

	boolean validateUser(int id, String user)throws UserNotFoundException;

	


}
