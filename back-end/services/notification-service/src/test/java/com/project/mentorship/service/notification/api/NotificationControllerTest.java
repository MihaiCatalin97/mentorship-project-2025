package com.project.mentorship.service.notification.api;

import static com.project.mentorship.contract.notification.model.NotificationDto.StatusEnum.PENDING;
import static com.project.mentorship.contract.notification.model.NotificationDto.TypeEnum.EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.contract.notification.model.NotificationDto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void create_ShouldReturn201() throws Exception {
		// given
		NotificationDto notificationDto = new NotificationDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
				UUID.fromString("223e4567-e89b-12d3-a456-426614174000"), EMAIL, PENDING);
		notificationDto.setId(null);
		notificationDto.setCreatedAt(null);
		notificationDto.setSentAt(null);

		// when & then
		mockMvc.perform(post("/notifications").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(notificationDto))).andExpect(status().isCreated());
	}
}
