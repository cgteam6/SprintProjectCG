package com.cg.oam.service;

import java.util.List;
import java.util.Set;

import com.cg.oam.entities.Admin;
import com.cg.oam.exception.UserNotFoundException;

public interface AdminService {


	Admin addAdmin(Admin admin)throws UserNotFoundException;

	Admin updateAdmin(Admin admin, int id) throws UserNotFoundException;

	Admin deleteAdmin(int id) throws UserNotFoundException;

	Admin viewAdmin(int id) throws UserNotFoundException;

	List<Admin> viewAllAdmins()throws UserNotFoundException;

	
	
}