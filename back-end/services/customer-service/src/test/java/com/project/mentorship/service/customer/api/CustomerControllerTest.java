package com.project.mentorship.service.customer.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.service.customer.CustomerServiceApplication;
import com.project.mentorship.service.customer.api.dto.CustomerDto;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = CustomerServiceApplication.class)
@AutoConfigureMockMvc
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void create_shouldReturn201_whenRequestIsValid() throws Exception {
		// Given
		CustomerDto customerDto = new CustomerDto(UUID.fromString("11111111-1111-1111-1111-111111111111"),
				UUID.fromString("33333333-3333-3333-3333-333333333333"), "Olivia", "Taylor",
				"olivia.taylor@example.com", "+40700111222", OffsetDateTime.parse("2025-02-10T14:30:00Z"),
				OffsetDateTime.parse("2025-09-20T08:45:00Z"));

		// When & Then
		mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customerDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.userId").isNotEmpty()).andExpect(jsonPath("$.firstName").value("Olivia"))
				.andExpect(jsonPath("$.lastName").value("Taylor"))
				.andExpect(jsonPath("$.email").value("olivia.taylor@example.com"))
				.andExpect(jsonPath("$.phone").value("+40700111222")).andExpect(jsonPath("$.createdAt").isNotEmpty())
				.andExpect(jsonPath("$.updatedAt").doesNotExist());
	}
}
