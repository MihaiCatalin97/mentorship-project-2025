package com.project.mentorship.service.billing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.contract.billing.model.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InvoiceLineMapperTest {

	private final InvoiceLineMapper invoiceLineMapper = new InvoiceLineMapper();

	@Test
	void mapToInvoiceLine_ShouldMapFromDtoToDomain_WhenGivenCorrectInput() {
		// Given
		UUID invoiceId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		String description = "description";
		Integer quantity = 1;
		Double price = 1.0;
		Double totalPrice = 1.0;
		OffsetDateTime createdAt = OffsetDateTime.now();

		InvoiceLineDto invoiceLineDto = new InvoiceLineDto(description, quantity, price, totalPrice);
		invoiceLineDto.setInvoiceId(invoiceId);
		invoiceLineDto.setCreatedAt(createdAt);

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(invoiceLineDto);

		// Then
		assertNotNull(invoiceLine);
		assertEquals(invoiceId, invoiceLine.getInvoiceId());
		assertEquals(description, invoiceLine.getDescription());
		assertEquals(quantity, invoiceLine.getQuantity());
		assertEquals(price, invoiceLine.getUnitPrice());
		assertEquals(totalPrice, invoiceLine.getTotal());
		assertEquals(createdAt, invoiceLine.getCreatedAt());
	}

	@Test
	void mapToInvoiceLine_shouldSetAllFieldsToNull_whenDtoFieldsAreNull() {
		// Given
		InvoiceLineDto invoiceLineDto = new InvoiceLineDto(null, null, null, null);

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(invoiceLineDto);

		// Then
		assertNotNull(invoiceLine);
		assertNull(invoiceLine.getId());
		assertNull(invoiceLine.getInvoiceId());
		assertNull(invoiceLine.getDescription());
		assertNull(invoiceLine.getQuantity());
		assertNull(invoiceLine.getUnitPrice());
		assertNull(invoiceLine.getTotal());
		assertNull(invoiceLine.getUpdatedAt());
	}

	@Test
	void mapToInvoiceLine_ShouldReturnNull_WhenDtoIsNull() {
		// Given
		InvoiceLineDto invoiceLineDto = null;

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(invoiceLineDto);

		// Then
		assertNull(invoiceLine);
	}

	@Test
	void mapToInvoiceLineDto_ShouldMapFromDomainToDto_WhenGivenCorrectInput() {
		// Given
		UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
		UUID invoiceId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		String description = "description";
		Integer quantity = 1;
		Double price = 1.0;
		Double totalPrice = 1.0;
		OffsetDateTime createdAt = OffsetDateTime.now();
		OffsetDateTime updatedAt = OffsetDateTime.now();

		InvoiceLine invoiceLine = new InvoiceLine(id, invoiceId, description, quantity, price, totalPrice, createdAt,
				updatedAt);

		// When
		InvoiceLineDto invoiceLineDto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNotNull(invoiceLineDto);
		assertEquals(invoiceId, invoiceLineDto.getInvoiceId());
		assertEquals(description, invoiceLineDto.getDescription());
		assertEquals(quantity, invoiceLineDto.getQuantity());
		assertEquals(price, invoiceLineDto.getUnitPrice());
		assertEquals(totalPrice, invoiceLineDto.getTotal());
		assertEquals(createdAt, invoiceLineDto.getCreatedAt());
	}

	@Test
	void mapToInvoiceLineDto_shouldSetAllFieldsToNull_whenInvoiceLineFieldsAreNull() {
		// Given
		InvoiceLine invoiceLine = new InvoiceLine();

		// When
		InvoiceLineDto invoiceLineDto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNotNull(invoiceLineDto);
		assertNull(invoiceLineDto.getId());
		assertNull(invoiceLineDto.getInvoiceId());
		assertNull(invoiceLineDto.getDescription());
		assertNull(invoiceLineDto.getQuantity());
		assertNull(invoiceLineDto.getUnitPrice());
		assertNull(invoiceLineDto.getTotal());
		assertNull(invoiceLineDto.getUpdatedAt());
	}

	@Test
	void mapToInvoiceLineDto_ShouldReturnNull_WhenDomainIsNull() {
		// Given
		InvoiceLine invoiceLine = null;

		// When
		InvoiceLineDto invoiceLineDto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNull(invoiceLineDto);
	}
}
