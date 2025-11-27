package com.project.mentorship.service.billing.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceLineRepositoryTest {

	private InvoiceLineRepository invoiceLineRepository;

	@BeforeEach
	void setUp() {
		invoiceLineRepository = new InvoiceLineRepository();
	}

	@Test
	void save_ShouldAddInvoiceLineToList_WhenGivenCorrectInput() {
		// Given
		InvoiceLine invoiceLine = new InvoiceLine();
		invoiceLine.setId(UUID.randomUUID());

		// When
		InvoiceLine result = invoiceLineRepository.save(invoiceLine);

		// Then
		assertNotNull(result);
		assertEquals(invoiceLine, result, "The saved invoice line should be the same as the input invoice line");
	}

	@Test
	void save_ShouldAssignId_WhenIdIsNull() {
		// Given
		InvoiceLineRepository repository = new InvoiceLineRepository();
		InvoiceLine invoiceLine = new InvoiceLine(); // ID is null

		// When
		InvoiceLine saved = repository.save(invoiceLine);

		// Then
		assertNotNull(saved.getId(), "ID should be generated when null");
	}
}
