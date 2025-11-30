package com.project.mentorship.service.auth.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void postUsers_shouldReturn201_whenRequestIsValid() throws Exception {
		// Given
		String body = """
				{
				  "username": "alex",
				  "email": "alex@gmail.com",
				  "password": "1234"
				}
				""";

		// When, Then
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username").value("alex")).andExpect(jsonPath("$.email").value("alex@gmail.com"))
				.andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.role").value("USER"))
				.andExpect(jsonPath("$.createdAt").isNotEmpty()).andExpect(jsonPath("$.updatedAt").isNotEmpty())
				.andExpect(jsonPath("$.password").doesNotExist());
	}
}
