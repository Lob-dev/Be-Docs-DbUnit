package com.bedocs.dbunit.domain;

public class UserDto {

	private String name;
	private String email;
	private Long age;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Long getAge() {
		return age;
	}

	public UserDto(String name, String email, Long age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public UserDto(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public UserDto(String name) {
		this.name = name;
	}

	public UserDto() {
	}
}
