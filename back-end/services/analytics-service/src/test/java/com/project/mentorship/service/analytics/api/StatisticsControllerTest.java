package com.project.mentorship.service.analytics.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.contract.analytics.model.StatisticsDto;
import com.project.mentorship.service.analytics.AnalyticsServiceApplication;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = AnalyticsServiceApplication.class)
@AutoConfigureMockMvc
class StatisticsControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createStatistics_shouldReturn201Created_whenRequestIsValid() throws Exception {
		// Given
		OffsetDateTime dateTime = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
		StatisticsDto statisticsDto = new StatisticsDto(dateTime, 10, 250.0);
		statisticsDto.setId(null);
		statisticsDto.setCreatedAt(null);
		String requestBody = objectMapper.writeValueAsString(statisticsDto);

		// When & Then
		mockMvc.perform(post("/statistics").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isCreated());
	}
}
