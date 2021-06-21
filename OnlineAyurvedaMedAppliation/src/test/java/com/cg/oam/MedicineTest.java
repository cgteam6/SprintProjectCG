package com.cg.oam;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Medicine;
import com.cg.oam.repository.MedicineRepository;
import com.cg.oam.service.MedicineService;
@SpringBootTest
class MedicineTest {

	@Autowired
	MedicineService medicineService;
	@MockBean
	MedicineRepository medicineRepository;
	@Test
	void testAddMedicine() {
		//fail("Not yet implemented");
		Medicine medicine =  getMedicine();
		when(medicineRepository.save(medicine)).thenReturn(medicine);
		assertEquals(medicineService.addMedicine(medicine), medicine);
	}

	

	@Test
	void testUpdateMedicine() {
		//fail("Not yet implemented");
		Medicine medicine = getMedicine();
		when(medicineRepository.save(medicine)).thenReturn(medicine);
		assertEquals(medicineService.updateMedicine(medicine), medicine);
	}

	
	private Medicine getMedicine() {
		Medicine medicine=new Medicine();

		medicine.setMedicineId(500);
		medicine.setMedicineName("paracytmal");
		

		return medicine;
	}

}
