package com.project.mentorship.service.billing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.billing.api.dto.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.time.OffsetDateTime;
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
		OffsetDateTime createdAt = OffsetDateTime.now();
<<<<<<< HEAD
		OffsetDateTime updatedAt = OffsetDateTime.now();

		InvoiceLineDto dto = new InvoiceLineDto(id, invoiceId, description, quantity, price, totalPrice, createdAt,
				updatedAt);
=======
        OffsetDateTime updatedAt = OffsetDateTime.now();

		InvoiceLineDto dto = new InvoiceLineDto(id, invoiceId, description, quantity, price, totalPrice, createdAt, updatedAt);
>>>>>>> 6917be0 (JVNGRS-44 Fixed code smells, added createdAt and updatedAt fields to InvoiceLine object)

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(dto);

		// Then
		assertNotNull(invoiceLine);
		assertEquals(invoiceId, invoiceLine.getInvoiceId());
		assertEquals(description, invoiceLine.getDescription());
		assertEquals(quantity, invoiceLine.getQuantity());
		assertEquals(price, invoiceLine.getUnitPrice());
		assertEquals(totalPrice, invoiceLine.getTotal());
<<<<<<< HEAD
		assertEquals(createdAt, invoiceLine.getCreatedAt());
		assertEquals(updatedAt, invoiceLine.getUpdatedAt());
	}

	@Test
	void mapToInvoiceLine_shouldSetAllFieldsToNull_whenDtoFieldsAreNull() {
		// Given
		InvoiceLineDto customerDto = new InvoiceLineDto(null, null, null, null, null, null, null, null);

		// When
		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(customerDto);

		// Then
		assertNotNull(invoiceLine);
		assertNull(invoiceLine.getId());
		assertNull(invoiceLine.getInvoiceId());
		assertNull(invoiceLine.getDescription());
		assertNull(invoiceLine.getQuantity());
		assertNull(invoiceLine.getUnitPrice());
		assertNull(invoiceLine.getTotal());
		assertNull(invoiceLine.getCreatedAt());
		assertNull(invoiceLine.getUpdatedAt());
=======
        assertEquals(createdAt, invoiceLine.getCreatedAt());
        assertEquals(updatedAt, invoiceLine.getUpdatedAt());
>>>>>>> 6917be0 (JVNGRS-44 Fixed code smells, added createdAt and updatedAt fields to InvoiceLine object)
	}

    @Test
    void mapToInvoiceLine_shouldSetAllFieldsToNull_whenDtoFieldsAreNull() {
        // Given
        InvoiceLineDto customerDto = new InvoiceLineDto(
                null, null, null, null, null, null, null, null
        );

        // When
        InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(customerDto);

        // Then
        assertNotNull(invoiceLine);
        assertNull(invoiceLine.getId());
        assertNull(invoiceLine.getInvoiceId());
        assertNull(invoiceLine.getDescription());
        assertNull(invoiceLine.getQuantity());
        assertNull(invoiceLine.getUnitPrice());
        assertNull(invoiceLine.getTotal());
        assertNull(invoiceLine.getCreatedAt());
        assertNull(invoiceLine.getUpdatedAt());
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
<<<<<<< HEAD
		OffsetDateTime createdAt = OffsetDateTime.now();
		OffsetDateTime updatedAt = OffsetDateTime.now();

		InvoiceLine invoiceLine = new InvoiceLine(id, invoiceId, description, quantity, price, totalPrice, createdAt,
				updatedAt);
=======
        OffsetDateTime createdAt = OffsetDateTime.now();
        OffsetDateTime updatedAt = OffsetDateTime.now();

		InvoiceLine invoiceLine = new InvoiceLine(id, invoiceId, description, quantity, price, totalPrice, createdAt, updatedAt);
>>>>>>> 6917be0 (JVNGRS-44 Fixed code smells, added createdAt and updatedAt fields to InvoiceLine object)

		// When
		InvoiceLineDto dto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNotNull(dto);
		assertEquals(id, dto.id());
		assertEquals(invoiceId, dto.invoiceId());
		assertEquals(description, dto.description());
		assertEquals(quantity, dto.quantity());
		assertEquals(price, dto.unitPrice());
		assertEquals(totalPrice, dto.total());
<<<<<<< HEAD
		assertEquals(createdAt, invoiceLine.getCreatedAt());
		assertEquals(updatedAt, invoiceLine.getUpdatedAt());
	}

	@Test
	void mapToInvoiceLineDto_shouldSetAllFieldsToNull_whenInvoiceLineFieldsAreNull() {
		// Given
		InvoiceLine invoiceLine = new InvoiceLine();

		// When
		InvoiceLineDto dto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

		// Then
		assertNotNull(dto);
		assertNull(dto.id());
		assertNull(dto.invoiceId());
		assertNull(dto.description());
		assertNull(dto.quantity());
		assertNull(dto.unitPrice());
		assertNull(dto.total());
		assertNull(dto.createdAt());
		assertNull(dto.updatedAt());
=======
        assertEquals(createdAt, invoiceLine.getCreatedAt());
        assertEquals(updatedAt, invoiceLine.getUpdatedAt());
>>>>>>> 6917be0 (JVNGRS-44 Fixed code smells, added createdAt and updatedAt fields to InvoiceLine object)
	}

    @Test
    void mapToInvoiceLineDto_shouldSetAllFieldsToNull_whenInvoiceLineFieldsAreNull() {
        // Given
        InvoiceLine invoiceLine = new InvoiceLine();

        // When
        InvoiceLineDto dto = invoiceLineMapper.mapToInvoiceLineDto(invoiceLine);

        // Then
        assertNotNull(dto);
        assertNull(dto.id());
        assertNull(dto.invoiceId());
        assertNull(dto.description());
        assertNull(dto.quantity());
        assertNull(dto.unitPrice());
        assertNull(dto.total());
        assertNull(dto.createdAt());
        assertNull(dto.updatedAt());
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
