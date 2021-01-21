package com.bedocs.dbunit.dao;

import com.bedocs.dbunit.domain.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class
})
class UserDaoTest {

	private DataSourceDatabaseTester dataSourceDatabaseTester;
	@Autowired
	private DataSource dataSource;

	private IDatabaseConnection iDatabaseConnection;

	private Connection connection;

	@BeforeEach
	public void setUp() throws Exception {
		dataSourceDatabaseTester = new DataSourceDatabaseTester(dataSource);
		IDataSet dataSet = new FlatXmlDataSet(getClass().getResource("/data.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(dataSourceDatabaseTester.getConnection(), dataSet);
		iDatabaseConnection = dataSourceDatabaseTester.getConnection();
		connection = iDatabaseConnection.getConnection();
	}

	@Test
	void givenDataSet_selectIdWithDao() throws Exception {

		ResultSet resultSet = connection.prepareStatement("SELECT * FROM USER").executeQuery();

		List<User> users = new ArrayList<>();
		while (resultSet.next()) {
			users.add(new User.Builder()
					.id(resultSet.getLong("id"))
					.name(resultSet.getString("name"))
					.email(resultSet.getString("email"))
					.age(resultSet.getString("age"))
					.build());
		}


		connection.prepareStatement("DELETE FROM USER WHERE ID = 1").executeUpdate();
		connection.prepareStatement("DELETE FROM USER WHERE ID = 2").executeUpdate();
		connection.prepareStatement("DELETE FROM USER WHERE ID = 3").executeUpdate();

		resultSet = connection.prepareStatement("SELECT * FROM USER").executeQuery();

		List<User> users2 = new ArrayList<>();
		while (resultSet.next()) {
			users2.add(new User.Builder()
					.id(resultSet.getLong("id"))
					.name(resultSet.getString("name"))
					.email(resultSet.getString("email"))
					.age(resultSet.getString("age"))
					.build());
		}

		System.out.println(users);
		System.out.println(users2);

		IDataSet dataSet = iDatabaseConnection.createDataSet();

	}


}