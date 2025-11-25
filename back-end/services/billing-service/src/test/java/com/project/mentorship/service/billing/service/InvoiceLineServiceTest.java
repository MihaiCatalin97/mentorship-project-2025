package com.project.mentorship.service.billing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.billing.domain.InvoiceLine;
import com.project.mentorship.service.billing.persistance.InvoiceLineRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InvoiceLineServiceTest {

	@Mock
	private InvoiceLineRepository invoiceLineRepository;

	@InjectMocks
	private InvoiceLineService invoiceLineService;

	private final InvoiceLine invoiceLine = new InvoiceLine().setId(UUID.randomUUID());

	@Test
	void create_ShouldSaveAndReturnInvoiceLine() throws Exception {
		// Given
		when(invoiceLineRepository.save(any(InvoiceLine.class))).thenReturn(invoiceLine);

		// When
		InvoiceLine result = invoiceLineService.create(invoiceLine);

		// Then
		assertNotNull(result);
		assertEquals(invoiceLine.getId(), result.getId());

	}
}
