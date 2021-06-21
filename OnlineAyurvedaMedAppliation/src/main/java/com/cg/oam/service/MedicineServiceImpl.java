package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;

//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.MedicineNotFoundException;
import com.cg.oam.repository.MedicineRepository;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {
	@Autowired
	MedicineRepository repository;

	//Logger logger = (Logger) LoggerFactory.logger(MedicineServiceImpl.class);

	@Override
	public Medicine addMedicine(Medicine medicine) {
		// TODO Auto-generated method stub
		//repository.save(medicineId);
	//	logger.info("*** Service: medicine added successfully. ***");
		return repository.save(medicine); 
		
	}

	@Override
	public Medicine viewMedicine(Integer medicineId) {
		// TODO Auto-generated method stub
//		logger.info("*** Service : Displaying Medicine Information. ***");
		return repository.findById(medicineId).get();
		
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
//logger.info("*** Service : Medicine Details updated successfully. ***" );
		
		return repository.save(medicine);
	
	}

	@Override
	public Medicine removeMedicine(int medicineId) throws MedicineNotFoundException {
		// TODO Auto-generated method stub
		Medicine medicine =  repository.findById(medicineId).orElse(null);
		if(repository.existsById(medicineId)) {
			repository.deleteById(medicineId);
		//	logger.info(" *** Service : Medicine removed Successfully. ***");
		}
		
		return medicine;
		
	}

	@Override
	public List<Medicine> showAllMedicine() {
		// TODO Auto-generated method stub
		List<Medicine> medicine = new ArrayList<Medicine>();
		repository.findAll().forEach(medicine1->medicine.add(medicine1));
		return medicine;
	}
	}
