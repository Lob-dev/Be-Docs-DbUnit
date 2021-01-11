package com.bedocs.dbunit.service;

import com.bedocs.dbunit.dao.UserDao;
import com.bedocs.dbunit.domain.User;
import com.bedocs.dbunit.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserDao userRepository;

	public UserService(UserDao userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAll() {
		return userRepository.getAll();
	}

	public void joinUser(UserDto user) {
		userRepository.joinUser(user);
	}

	public void deleteUser(Long userid) {
		userRepository.deleteUser(userid);
	}
}
