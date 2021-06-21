package com.cg.oam.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.cg.oam.service.AdminService;


//
//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("OnlineAyurvedaMedApplication/admin")
@Validated
public class AdminController {
	@Autowired
	AdminService service;
	
	
//	 public AdminController() {
//		// TODO Auto-generated constructor stub
//	 
////		service= new AdminServceImpl();
//	}
	
//	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// add admin details
		@PostMapping("/addAdmin")
		public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
			Admin adminData = service.addAdmin(admin);
	        if(adminData==null)
	        {
//	        	logger.error("Controller: Failed to add admin");
	        	throw new UserNotFoundException("Admin not added");
	        }
		
//	        logger.info("*** Controller : Admin added successfully. ***");
			return new ResponseEntity<Admin>(adminData, HttpStatus.OK);
		}
		
		// update admin details with Id
//		@PutMapping("/updateAdmin/{adminId}")
//		public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin,@PathVariable ("id") int id) {
//			Admin adminData = service.updateAdmin(admin,id);
//			 if(adminData==null)
//		        {
////				 logger.error("Controller: Failed to update admin");
//				 throw new UserNotFoundException("Admin not updated");
//		        }
//			
////			 logger.info("*** Controller : Admin updated successfully. ***");
//			return new ResponseEntity<Admin>(adminData, HttpStatus.OK);
//		}
		
		
		@PutMapping("/updateAdmin/{adminId}")
		public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, @PathVariable ("AdminId") int AdminId) {
			Admin adminData = service.updateAdmin(admin,AdminId);
			if (adminData == null) {
//				logger.error("Controller: Failed to update user");
				throw new UserNotFoundException("User not updated");
			}
//			logger.info("*** Controller : User updated successfully. ***");
			return new ResponseEntity<Admin>(adminData, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/deleteAdmin/{adminId}")
		public ResponseEntity<Admin> deleteAdmin(@PathVariable int AdminId) {
//			logger.warn("Controller: Removing admin");
			Admin adminData = service.deleteAdmin(AdminId);
			 if (adminData == null) { 
//				 logger.error("Controller: Admin Not Found For given id : " + adminId);
				 throw new UserNotFoundException("No Admin for given Id "+AdminId );
				 }
//			 logger.info("*** Controller : Admin removed. ***");
			return new ResponseEntity<Admin>(adminData, HttpStatus.OK);
		}

		// view admin details with Id
		@GetMapping("/viewAdmin/{adminId}")
		public ResponseEntity<Admin> viewAdmin(@PathVariable int AdminId) {
			Admin adminData = service.viewAdmin(AdminId);
			
			 if (adminData == null) { 
//				 logger.error("Controller: Admin not found.");
				 throw new UserNotFoundException("No Admin for given Id "+AdminId );
				 }
			 
//			logger.info("*** Controller : Displaying admin list. ***");
			return new ResponseEntity<Admin>(adminData, HttpStatus.OK);
		}
		@GetMapping("/viewAllAdmins")
		public ResponseEntity<List<Admin>> viewAllAdmins() {
			List<Admin> AdminList=service.viewAllAdmins();
			if (AdminList == null) { 
//				 logger.error("Controller: Admin not found.");
				 throw new UserNotFoundException("No Admin for given Id "+AdminList );
				 }
			return new ResponseEntity<List<Admin>>(AdminList, HttpStatus.OK);
		}
}
