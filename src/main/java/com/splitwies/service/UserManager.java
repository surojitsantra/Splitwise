package com.splitwies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.splitwies.model.User;

@Service
public class UserManager {
	
	private List<User> users = new ArrayList<>();
	private static Long USER_ID_CTR = 1L;
	
//	@Autowired
//	private UserRepo userRepo;

	public User addNewUser(String userName) {
		User user = new User();
		user.setUserId(USER_ID_CTR++);
		user.setUserName(userName);
//		return userRepo.save(user);
		users.add(user);
		return user;
	}

	public List<User> gettAllUsers() {
//		return userRepo.findAll();
		return users;
	}

	public User getUserById(Long userId) {
//		return userRepo.findById(userId).orElse(null);
		return users.stream().filter(user -> user.getUserId() == userId).findFirst().orElse(null);
	}

	public List<User> getUsers(List<Long> userIds) {
//		return userRepo.findByUserIdIn(userIds);
		return users.stream().filter(user -> userIds.contains(user.getUserId())).collect(Collectors.toList());
	}
}
