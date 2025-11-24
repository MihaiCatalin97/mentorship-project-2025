package com.project.mentorship.service.reservation.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateReservationAndReturn201() throws Exception {
		// Given
		ReservationDto request = new ReservationDto("550e8400-e29b-41d4-a716-446655440000",
				"123e4567-e89b-12d3-a456-426614174000", "223e4567-e89b-12d3-a456-426614174000", "2025-11-14T10:00:00Z",
				"2025-11-14T12:00:00Z", "PENDING", "2025-11-14T09:50:00Z", "2025-11-14T09:50:00Z");

		// When & Then
		mockMvc.perform(post("/reservations").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.customerId").value("123e4567-e89b-12d3-a456-426614174000"))
				.andExpect(jsonPath("$.status").value("PENDING")).andExpect(jsonPath("$.createdAt").isNotEmpty())
				.andExpect(jsonPath("$.updatedAt").doesNotExist());

	}
}
