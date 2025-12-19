package com.project.mentorship.service.vehicle.api;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.contract.vehicle.model.VehicleTypeDto;
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
		VehicleTypeDto vehicleTypeDto = new VehicleTypeDto("Dacia Logan 2022", 25.5, 5);
		vehicleTypeDto.setId(null);
		vehicleTypeDto.setCreatedAt(null);
		vehicleTypeDto.setUpdatedAt(null);

		// When & Then
		mockMvc.perform(post("/vehicles/types").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vehicleTypeDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty()).andExpect(jsonPath("$.name").value("Dacia Logan 2022"))
				.andExpect(jsonPath("$.hourlyRate").value(25.5)).andExpect(jsonPath("$.capacity").value(5))
				.andExpect(jsonPath("$.createdAt").isNotEmpty()).andExpect(jsonPath("$.updatedAt", nullValue()));
	}
}
