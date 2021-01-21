package com.bedocs.dbunit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void userController_findAll() throws Exception {
		mockMvc.perform(get("/api/users"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void userController_delete() throws Exception {
		mockMvc.perform(delete("/api/users/{userid}", 1L))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
