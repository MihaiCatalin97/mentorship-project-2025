package com.project.mentorship.service.billing.mapper;

import com.project.mentorship.contract.billing.model.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import org.springframework.stereotype.Component;

@Component
public final class InvoiceLineMapper {

	public InvoiceLine mapToInvoiceLine(InvoiceLineDto dto) {
		if (dto == null) {
			return null;
		}
		return InvoiceLine.builder().id(dto.getId() != null ? dto.getId() : null)
				.invoiceId(dto.getInvoiceId() != null ? dto.getInvoiceId() : null)
				.description(dto.getDescription() != null ? dto.getDescription() : null)
				.quantity(dto.getQuantity() != null ? dto.getQuantity() : null)
				.unitPrice(dto.getUnitPrice() != null ? dto.getUnitPrice() : null)
				.total(dto.getTotal() != null ? dto.getTotal() : null)
				.createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : null)
				.updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : null).build();
	}

	public InvoiceLineDto mapToInvoiceLineDto(InvoiceLine invoiceLine) {
		if (invoiceLine == null) {
			return null;
		}

		InvoiceLineDto dto = new InvoiceLineDto();

		dto.setId(invoiceLine.getId());
		dto.setInvoiceId(invoiceLine.getInvoiceId());
		dto.setDescription(invoiceLine.getDescription());
		dto.setQuantity(invoiceLine.getQuantity());
		dto.setUnitPrice(invoiceLine.getUnitPrice());
		dto.setTotal(invoiceLine.getTotal());
		dto.setCreatedAt(invoiceLine.getCreatedAt());
		dto.setUpdatedAt(invoiceLine.getUpdatedAt());

		return dto;
	}
}
