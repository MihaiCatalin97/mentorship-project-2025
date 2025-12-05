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
		assertEquals(invoiceLine, result);
	}

	@Test
	void save_ShouldReturnSameInstance_WhenSavingInvoiceLine() {
		// Given
		InvoiceLine invoiceLine = new InvoiceLine();

		// When
		InvoiceLine saved = invoiceLineRepository.save(invoiceLine);

		// Then
		assertEquals(invoiceLine, saved);
	}
}
