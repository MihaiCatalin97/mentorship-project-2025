package com.project.mentorship.service.billing.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record InvoiceLineDto(UUID id, UUID invoiceId, String description, Integer quantity, Double unitPrice,
		Double total, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
