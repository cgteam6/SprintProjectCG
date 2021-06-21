package com.cg.oam.service;



import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
//import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oam.entities.Admin;
import com.cg.oam.entities.User;
import com.cg.oam.exception.UserNotFoundException;
import com.cg.oam.repository.AdminRepository;



@Service
@Transactional
public class AdminServceImpl implements AdminService {
	
	@Autowired
		AdminRepository repository;

//	Logger logger = (Logger) LoggerFactory.logger(AdminServceImpl.class);

		// Method to add admin
		
		
		@Override
		public Admin addAdmin(Admin admin) throws UserNotFoundException {
			// TODO Auto-generated method stub

			if(admin==null){
				throw new UserNotFoundException("Admin not added");
				
			}
			Admin adminData = repository.save(admin);
//			logger.info("* Service :  Admin added successfully. *");
			return adminData;
		}

		// Method to update admin
		

		@Override
		public Admin updateAdmin(Admin admin, int id) throws UserNotFoundException{
			
//			Admin admin1= repository.findById(admin).orElse(null);
//			return repository.save(admin);
			Optional<Admin> admin1 = repository.findById(admin.getId());
			if(admin1!=null)
			{
				Admin adm=admin1.get();
				adm.setId(admin.getId());
				adm.setName(admin.getName());
				adm.setPassword(admin.getPassword());
				adm.setUser(admin.getUser());
				
		
//			logger.info("* Service : Admin updated successfully. *");
			return repository.save(adm);
		}
			else
			{
//				logger.error("*** Service : Admin updated Failed. ***");
				throw new UserNotFoundException("Admin not updated");
			}
		}

		// This method remove the admin with id in table
		
		@Override
		public Admin deleteAdmin(int AdminId) throws UserNotFoundException {
			// TODO Auto-generated method stub
			Admin admin = repository.findById(AdminId).orElse(null);
			if(admin!=null){
			repository.deleteById(AdminId);
//			logger.warn("* Removing Admin. *");
//			if (repository.existsById(Id)) {
//				repository.deleteById(Id);
//				logger.info("* Service : Admin removed. *");
				return admin;
			}

			else
			{
//				logger.error("*** Service : Admin Deleted. ***");
				throw new UserNotFoundException("Admin not deleted");
			}
			
		}

		// This method view the admin with id in table
		
		@Override
		public Admin viewAdmin(int AdminId) throws UserNotFoundException{
			// TODO Auto-generated method stub
			Admin admin = repository.findById(AdminId).orElse(null);
//			return repository.findById(id);
			if(admin==null) {
//			logger.info("* Service : Displaying admin information. *");
				throw new UserNotFoundException("Admin not added");}
				
				return admin;
			
			
		}
		
		public List<Admin> viewAllAdmins() throws UserNotFoundException {
			
			
			
			List<Admin> AdminList= repository.findAll();
			
			
			if (AdminList.isEmpty()) {
				throw new UserNotFoundException("Empty");}
			
//			
//			Set<Admin> admin=new HashSet<Admin>();
//			repository.findAll().forEach(admin1->admin.add(admin1));
			return AdminList;
		}

	

	}
