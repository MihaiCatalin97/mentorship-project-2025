package com.project.mentorship.service.vehicle.api;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
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
	void create_shouldReturn201_whenRequestIsValid() throws Exception {
		// Given
		VehicleTypeDto request = new VehicleTypeDto((UUID) null, "Dacia Logan 2022", 25.5, 5, (OffsetDateTime) null,
				(OffsetDateTime) null);

		// When & Then
		mockMvc.perform(post("/vehicles/types").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.name").value("Dacia Logan 2022"))
				.andExpect(jsonPath("$.hourlyRate").value(25.5)).andExpect(jsonPath("$.capacity").value(5))
				.andExpect(jsonPath("$.createdAt").isNotEmpty()).andExpect(jsonPath("$.updatedAt", nullValue()));
	}
}
