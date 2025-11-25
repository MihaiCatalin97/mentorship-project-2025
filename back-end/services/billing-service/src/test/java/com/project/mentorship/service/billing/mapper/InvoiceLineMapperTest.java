package com.project.mentorship.service.billing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.billing.api.dto.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class InvoiceLineMapperTest {

	private final InvoiceLineMapper invoiceLineMapper = new InvoiceLineMapper();

	@Test
	void mapToInvoiceLine_ShouldMapFromDtoToDomain_WhenGivenCorrectInput() {
		// Given
		UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
		UUID invoiceId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		String description = "description";
		Integer quantity = 1;
		Double price = 1.0;
		Double totalPrice = 1.0;

		InvoiceLineDto dto = new InvoiceLineDto(id, invoiceId, description, quantity, price, totalPrice);

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(dto);

		// Then
		assertNotNull(invoiceLine);
		assertEquals(invoiceId, invoiceLine.getInvoiceId());
		assertEquals(description, invoiceLine.getDescription());
		assertEquals(quantity, invoiceLine.getQuantity());
		assertEquals(price, invoiceLine.getUnitPrice());
		assertEquals(totalPrice, invoiceLine.getTotal());
	}

	@Test
	void mapToInvoiceLine_ShouldReturnNull_WhenDtoIsNull() {
		// Given
		InvoiceLineDto dto = null;

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(dto);

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

		InvoiceLine invoiceLine = new InvoiceLine(id, invoiceId, description, quantity, price, totalPrice);

		// When
		InvoiceLineDto invoiceLineDto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNotNull(invoiceLineDto);
		assertEquals(id, invoiceLineDto.id());
		assertEquals(invoiceId, invoiceLineDto.invoiceId());
		assertEquals(description, invoiceLineDto.description());
		assertEquals(quantity, invoiceLineDto.quantity());
		assertEquals(price, invoiceLineDto.unitPrice());
		assertEquals(totalPrice, invoiceLineDto.total());
	}

	@Test
	void mapToInvoiceLineDto_ShouldReturnNull_WhenDomainIsNull() {
		// Given
		InvoiceLine invoiceLine = null;

		// When
		InvoiceLineDto dto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNull(dto);
	}
}
