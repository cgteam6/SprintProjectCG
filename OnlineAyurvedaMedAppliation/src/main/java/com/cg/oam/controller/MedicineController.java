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


import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.MedicineNotFoundException;
import com.cg.oam.service.MedicineService;
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("onlineayurveda/medicine")
public class MedicineController {
	@Autowired
	MedicineService medicineService;
//	Logger logger = LoggerFactory.getLogger(MedicineController.class);
	
	/*@PostMapping("/medicineDetails/{medicineId}")
	public Medicine addMedicine(Medicine medicineId) {
		return medicineService.addMedicine(medicineId);
	} */
	
	@PostMapping("/addMedicine")
	public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {
		Medicine medicineData = medicineService.addMedicine(medicine);
        if(medicineData==null)
        {
        //	logger.error("Controller: Failed to add admin");
        	throw new MedicineNotFoundException("Medicine not added");
        }
	
     //   logger.info("*** Controller : Admin added successfully. ***");
		return new ResponseEntity<Medicine>(medicineData, HttpStatus.OK);
	}
	
	//creating get mapping that retrives all medicines..
	@GetMapping("/medicineDetails")
	public List<Medicine>showAllMedicine(){
		return medicineService.showAllMedicine();
	}
	
	//viewing specific medicine..
	/*@GetMapping("/medicineDetails/{medicineId}")
	public Medicine viewMedicine(@PathVariable("medicineId")String medicineId) {
		return medicineService.viewMedicine(medicineId);
	}*/
	@GetMapping("/viewMedicine/{medicineId}")
	public ResponseEntity<Medicine> viewMedicine(@PathVariable int medicineId) {
		Medicine medicineData = medicineService.viewMedicine(medicineId);
		
		 if (medicineData == null) { 
	//		 logger.error("Controller: Medicine not found.");
			 throw new MedicineNotFoundException("No Medicine is for given Id "+medicineId );
			 }
		 
	//	logger.info("*** Controller : Displaying mediciine list. ***");
		return new ResponseEntity<Medicine>(medicineData, HttpStatus.OK);
	}
	
	//*******upadting medicine details..********
	/*@PutMapping("/medicine")
	public Medicine updateMedicine(@RequestBody Medicine medicine) throws MedicineNotFoundException{
		 medicineService.updateMedicine(medicine);
		 return medicine;
	}*/
	@PutMapping("/updateMedicine/{medicineId}")
	public ResponseEntity<Medicine> updateMedicine(@Valid @RequestBody Medicine medicine) {
		Medicine medicineData = medicineService.updateMedicine(medicine);
		 if(medicineData==null)
	        {
	//		 logger.error("Controller: Failed to update medicine");
			 throw new MedicineNotFoundException("Medicine not updated");
	        }
		
	//	 logger.info("*** Controller : Medicine updated successfully. ***");
		return new ResponseEntity<Medicine>(medicineData, HttpStatus.OK);
	}
	
	//  *****deleting medicine details..*****
	/*@DeleteMapping("/medicineDetails/{medicineId}")
	public Medicine removeMedicine(@PathVariable("medicineId")String medicineId) throws MedicineNotFoundException{
		medicineService.removeMedicine(medicineId);
		return removeMedicine(medicineId);
	} */
	@DeleteMapping("/removeMedicine/{medicineId}")
	public ResponseEntity<Medicine> removeMedicine(@PathVariable int medicineId) {
	//	logger.warn("Controller: Removing medicine");
		Medicine medicineData = medicineService.removeMedicine(medicineId);
		 if (medicineData == null) { 
		//	 logger.error("Controller: Medicine Not Found For given id : " + medicineId);
			 throw new MedicineNotFoundException("No Medicine for given Id "+medicineId );
			 }
	//	 logger.info("*** Controller : Medicine removed. ***");
		return new ResponseEntity<Medicine>(medicineData, HttpStatus.OK);
	}

}
