package com.bedocs.dbunit.domain;

public class User {

	private Long id;
	private String name;
	private String email;
	private String age;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAge() {
		return age;
	}

	public static class Builder {

		private Long id;
		private String name;
		private String email;
		private String age;

		public Builder id(Long val) {
			id = val;
			return this;
		}

		public Builder name(String val) {
			name = val;
			return this;
		}

		public Builder email(String val) {
			email = val;
			return this;
		}

		public Builder age(String val) {
			age = val;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	public User() {
	}

	private User(Builder builder) {
		id = builder.id;
		name = builder.name;
		email = builder.email;
		age = builder.age;
	}
}