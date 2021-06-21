package com.cg.oam.service;

import java.util.List;

import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.MedicineNotFoundException;

public interface MedicineService {
	Medicine addMedicine(Medicine var1);

	Medicine updateMedicine(Medicine var1) throws MedicineNotFoundException;

	Medicine removeMedicine(int var1) throws MedicineNotFoundException;

	List<Medicine> showAllMedicine();



	Medicine viewMedicine(Integer medicineId);

}
