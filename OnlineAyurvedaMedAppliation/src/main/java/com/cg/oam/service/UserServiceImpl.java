package com.cg.oam.service;

import com.cg.oam.entities.User;
import com.cg.oam.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.cg.oam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;




@Service
@Transactional

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

//	Logger logger = (Logger) LoggerFactory.logger(UserService.class);

	@Override
	public User addUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(user==null) {
			throw new UserNotFoundException("User not added");
		}
		  User user1 = new User();
	        user1.setUserId(user.getUserId());
	        user1.setUserName(user.getUserName());
	        user1.setUserType(user.getUserType());
	        User userData = repository.save(user1);
//			logger.info("*** Service :  User added successfully. ***");
			return userData;
	}
	@Override
	public User updateUser(User user, int userId)throws UserNotFoundException {
		// TODO Auto-generated method stub
	
//		User user= repository.findById(userId).orElse(null);
//		return repository.save(user);
		Optional<User> user1 = repository.findById(user.getUserId());
		if(user1!=null)
		{
			User us=user1.get();
			us.setUserId(user.getUserId());
			us.setUserName(user.getUserName());
			us.setUserType(user.getUserType());
//			logger.info("*** Service : User updated successfully. ***");
			return repository.save(us);

		}
		else
		{
//			logger.error("*** Service : User updated Failed. ***");
			throw new UserNotFoundException("User not updated");
		}
	}

		
	
	@Override
	public User removeUser(int userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = repository.findById(userId).orElse(null);
//		repository.deleteById(userId);
//		logger.warn("*** Removing User. ***");
		if (repository.existsById(userId)) {
			repository.deleteById(userId);
//			logger.info("*** Service : User removed. ***");
			return user;
		}
		
		else {
			throw new UserNotFoundException("User not removed");
		}
	}

	@Override
	public List<User> showAllUsers() {
		// TODO Auto-generated method stub
//		Set<User> user=new HashSet<User>();
		List<User> user = repository.findAll();
		if (user.isEmpty()) {
			throw new UserNotFoundException("Empty");}
//		List<User> user=new ArrayList<User>(user);
//		repository.findAll().forEach(user1->user.add(user1));
		return user;
	}

	@Override
	public boolean validateUser(int userId, String userName) {
		// TODO Auto-generated method stub
		
		List<User> user = repository.findAll();
	      if (user == null || user.isEmpty()) {

	         return false;

	      }

	      for (User user1 : user) {

	         if (user1.equals(userId) && user1.equals(userName)) {

	             return true;

	      }

	    }

	    return false;

	  }


}
