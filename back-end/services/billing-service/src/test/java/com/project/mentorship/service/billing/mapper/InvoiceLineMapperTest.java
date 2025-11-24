package com.project.mentorship.service.billing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.billing.api.dto.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class InvoiceLineMapperTest {

	private final InvoiceLineMapper invoiceLineMapper = new InvoiceLineMapper();

	@Test
	void mapToInvoiceLine_ShouldMapFromDtoToDomain() {

		UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
		UUID invoiceId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		String description = "description";
		Integer quantity = 1;
		Double price = 1.0;
		Double totalPrice = 1.0;

		InvoiceLineDto dto = new InvoiceLineDto(id, invoiceId, description, quantity, price, totalPrice);

		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(dto);

		assertNotNull(invoiceLine);
		assertEquals(invoiceId, invoiceLine.getInvoiceId());
		assertEquals(description, invoiceLine.getDescription());
		assertEquals(quantity, invoiceLine.getQuantity());
		assertEquals(price, invoiceLine.getUnitPrice());
		assertEquals(totalPrice, invoiceLine.getTotal());
	}

	@Test
	void mapToInvoiceLine_ShouldReturnNull_WhenDtoIsNull() {

		InvoiceLineDto dto = null;

		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(dto);

		assertNull(invoiceLine);
	}

	@Test
	void mapToInvoiceLineDto_ShouldMapFromDomainToDto() {

		UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
		UUID invoiceId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		String description = "description";
		Integer quantity = 1;
		Double price = 1.0;
		Double totalPrice = 1.0;

		InvoiceLine invoiceLine = new InvoiceLine(id, invoiceId, description, quantity, price, totalPrice);

		InvoiceLineDto invoiceLineDto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

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

		InvoiceLine invoiceLine = null;

		InvoiceLineDto dto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		assertNull(dto);
	}
}