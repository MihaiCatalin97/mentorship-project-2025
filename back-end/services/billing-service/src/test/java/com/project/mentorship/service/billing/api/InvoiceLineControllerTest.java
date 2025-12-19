package com.project.mentorship.service.billing.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.mentorship.contract.billing.model.InvoiceLineDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class InvoiceLineControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void create_shouldCreateInvoiceLineThenReturn201_whenGivenCorrectInput() throws Exception {
		// Given
		InvoiceLineDto invoiceLineDto = new InvoiceLineDto("Updated description", 12, 100.50, 1200.70);

		// When & Then
		mockMvc.perform(post("/billing/invoice-line").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invoiceLineDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.description").value("Updated description"))
				.andExpect(jsonPath("$.quantity").value(12)).andExpect(jsonPath("$.unitPrice").value(100.50))
				.andExpect(jsonPath("$.total").value(1200.70));
	}
}
