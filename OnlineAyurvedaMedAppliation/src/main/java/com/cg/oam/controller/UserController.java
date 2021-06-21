package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.entities.Admin;
import com.cg.oam.entities.User;
import com.cg.oam.exception.UserNotFoundException;
import com.cg.oam.service.UserService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/OnlineAyurvedaMedApplication/user")
public class UserController {
	
	@Autowired
	// private UserService userService;
	UserService service;

//	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User userData = service.addUser(user);
		if (userData == null) {
//			logger.error("Controller: Failed to add user");
			throw new UserNotFoundException("User not added");
		}
//		logger.info("*** Controller : User added successfully. ***");
		return new ResponseEntity<User>(userData, HttpStatus.OK);
	}
	
	
	// Method to update user details with Id
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable ("userId") int userId) {
		User userData = service.updateUser(user,userId);
		if (userData == null) {
//			logger.error("Controller: Failed to update user");
			throw new UserNotFoundException("User not updated");
		}
//		logger.info("*** Controller : User updated successfully. ***");
		return new ResponseEntity<User>(userData, HttpStatus.OK);
	}
	
	
	
	//Method to remove user details with Id
		@DeleteMapping("/removeUser/{userId}")
		public ResponseEntity<User> deleteAdmin(@PathVariable int userId) {

//			logger.warn("Controller: Removing user");
			User userData = service.removeUser(userId);
			if (userData == null) {
//				logger.error("Controller: User Not Found For given id : " + userId);
				throw new UserNotFoundException("No user present for id: " + userId);
			}
//			logger.info("*** Controller : User removed. ***");
			return new ResponseEntity<User>(userData, HttpStatus.OK);
		}
		
		@GetMapping("/showAllUsers")
		public ResponseEntity<List<User>> showAllUsers() {
			List<User> UserList=service.showAllUsers();
			if (UserList == null) { 
//				 logger.error("Controller: User not found.");
				 throw new UserNotFoundException("No Admin for given Id "+UserList );
				 }
			return new ResponseEntity<List<User>>(UserList, HttpStatus.OK);
		}
		
		
 		@GetMapping("/validateUser")
		public ResponseEntity<Boolean> validateUser(int userId, String userName) {

			
			Boolean userData = service.validateUser(userId, userName);
			if (userData == false) {
//				logger.error("*** Controller : Invalid Credentials ***");
				throw new UserNotFoundException("Invalid Credentials");
			}
			
//			logger.info("*** Controller : Successfull Login ***");
			return new ResponseEntity<Boolean>(userData, HttpStatus.OK);
			
		}
}
