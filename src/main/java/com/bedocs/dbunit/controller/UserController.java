package com.bedocs.dbunit.controller;

import com.bedocs.dbunit.domain.User;
import com.bedocs.dbunit.domain.UserDto;
import com.bedocs.dbunit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/", produces = "application/json; UTF-8")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getAll();
		if (CollectionUtils.isEmpty(users)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody UserDto user) {
		userService.joinUser(user);
		return ResponseEntity.status(HttpStatus.OK).body("성공");
	}

	@DeleteMapping("/users/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userid) {
		userService.deleteUser(userid);
		return ResponseEntity.status(HttpStatus.OK).body("삭제");
	}
}
