package com.project.mentorship.service.vehicle.api;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleStatus;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleTypeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateVehicleTypeAndReturn201() throws Exception {
		// Given
		VehicleTypeDto request = new VehicleTypeDto((UUID) null,
				"B-123-XYZ", "Dacia", "Logan", 2022, VehicleStatus.AVAILABLE, "Bucharest",
                (OffsetDateTime) null, (OffsetDateTime) null);

		// When & Then
		mockMvc.perform(post("/vehicles/types").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.licensePlate").value("B-123-XYZ"))
				.andExpect(jsonPath("$.brand").value("Dacia")).andExpect(jsonPath("$.model").value("Logan"))
				.andExpect(jsonPath("$.year").value(2022)).andExpect(jsonPath("$.status").value("AVAILABLE"))
				.andExpect(jsonPath("$.location").value("Bucharest")).andExpect(jsonPath("$.createdAt").isNotEmpty())
				.andExpect(jsonPath("$.updatedAt", nullValue()));
	}
}
