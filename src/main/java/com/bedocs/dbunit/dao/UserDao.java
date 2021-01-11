package com.bedocs.dbunit.dao;

import com.bedocs.dbunit.domain.User;
import com.bedocs.dbunit.domain.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

	private final JdbcTemplate jdbcTemplate;

	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper<User> rowMapper() {
		return (rs, rowNum) -> {
			return new User.Builder()
					.id(rs.getLong("id"))
					.name(rs.getString("name"))
					.email(rs.getString("email"))
					.age(rs.getString("age"))
					.build();
		};
	}


	public List<User> getAll() {
		final String sql = "SELECT * FROM USER";
		return jdbcTemplate.query(sql, rowMapper());
	}

	public void joinUser(UserDto user) {
		final String sql = "INSERT INTO USER (name, email, age) VALUES(?,?,?)";
		int update = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge());
		if (update < 1) {
			throw new RuntimeException("update 실패");
		}
	}

	public void deleteUser(Long userid) {
		final String sql = "DELETE FROM USER WHERE ID = ?";
		int update = jdbcTemplate.update(sql, userid);
		if (update < 1) {
			throw new RuntimeException("delete 실패");
		}
	}

	public User findUserById(Long userid) {
		final String sql = "SELECT * FROM USER WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), userid, User.class);
	}

}
