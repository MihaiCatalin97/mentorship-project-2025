package com.project.mentorship.service.auth.api;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.project.mentorship.service.auth.config.TestSecurityConfig;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
class UserApiDelegateImplTest {

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
				.andExpect(jsonPath("$.createdAt").isNotEmpty()).andExpect(jsonPath("$.updatedAt").value(nullValue()))
				.andExpect(jsonPath("$.password").doesNotExist());
	}

	@Test
	void findById_shouldReturn404_whenUserDoesNotExist() throws Exception {
		// When, Then
		mockMvc.perform(get("/users/00000000-0000-0000-0000-000000000000")).andExpect(status().isNotFound());
	}

	@Test
	void findById_shouldReturn200AndUser_whenUserExists() throws Exception {
		// Given
		String body = """
				{
				  "username": "alex",
				  "email": "alex@gmail.com",
				  "password": "1234"
				}
				""";

		String response = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		String userId = JsonPath.read(response, "$.id");

		// When, Then
		mockMvc.perform(get("/users/" + userId)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username").value("alex")).andExpect(jsonPath("$.email").value("alex@gmail.com"))
				.andExpect(jsonPath("$.id").value(userId)).andExpect(jsonPath("$.role").value("USER"))
				.andExpect(jsonPath("$.createdAt").isNotEmpty()).andExpect(jsonPath("$.updatedAt").value(nullValue()))
				.andExpect(jsonPath("$.password").doesNotExist());
	}

}
