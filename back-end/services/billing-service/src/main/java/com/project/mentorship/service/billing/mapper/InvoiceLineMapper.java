package com.project.mentorship.service.billing.mapper;

import com.project.mentorship.service.billing.api.dto.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import org.springframework.stereotype.Component;

@Component
public final class InvoiceLineMapper {

	public InvoiceLine mapToInvoiceLine(InvoiceLineDto dto) {
		if (dto == null) {
			return null;
		}
		return InvoiceLine.builder().id(dto.id() != null ? dto.id() : null)
				.invoiceId(dto.invoiceId() != null ? dto.invoiceId() : null)
				.description(dto.description() != null ? dto.description() : null)
				.quantity(dto.quantity() != null ? dto.quantity() : null)
				.unitPrice(dto.unitPrice() != null ? dto.unitPrice() : null)
				.total(dto.total() != null ? dto.total() : null)
				.createdAt(dto.createdAt() != null ? dto.createdAt() : null)
				.updatedAt(dto.updatedAt() != null ? dto.updatedAt() : null).build();
	}

	public InvoiceLineDto mapToInvoiceLineDto(InvoiceLine invoiceLine) {
		if (invoiceLine == null) {
			return null;
		}
		return new InvoiceLineDto(invoiceLine.getId() != null ? invoiceLine.getId() : null,
				invoiceLine.getInvoiceId() != null ? invoiceLine.getInvoiceId() : null,
				invoiceLine.getDescription() != null ? invoiceLine.getDescription() : null,
				invoiceLine.getQuantity() != null ? invoiceLine.getQuantity() : null,
				invoiceLine.getUnitPrice() != null ? invoiceLine.getUnitPrice() : null,
				invoiceLine.getTotal() != null ? invoiceLine.getTotal() : null,
				invoiceLine.getCreatedAt() != null ? invoiceLine.getCreatedAt() : null,
				invoiceLine.getUpdatedAt() != null ? invoiceLine.getUpdatedAt() : null);

	}
}
