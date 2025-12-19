package com.project.mentorship.service.billing.mapper;

import com.project.mentorship.contract.billing.model.InvoiceLineDto;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import org.springframework.stereotype.Component;

@Component
public final class InvoiceLineMapper {

	public InvoiceLine mapToInvoiceLine(InvoiceLineDto invoiceLineDto) {
		if (invoiceLineDto == null) {
			return null;
		}

		return InvoiceLine.builder().id(invoiceLineDto.getId() != null ? invoiceLineDto.getId() : null)
				.invoiceId(invoiceLineDto.getInvoiceId() != null ? invoiceLineDto.getInvoiceId() : null)
				.description(invoiceLineDto.getDescription() != null ? invoiceLineDto.getDescription() : null)
				.quantity(invoiceLineDto.getQuantity() != null ? invoiceLineDto.getQuantity() : null)
				.unitPrice(invoiceLineDto.getUnitPrice() != null ? invoiceLineDto.getUnitPrice() : null)
				.total(invoiceLineDto.getTotal() != null ? invoiceLineDto.getTotal() : null)
				.createdAt(invoiceLineDto.getCreatedAt() != null ? invoiceLineDto.getCreatedAt() : null)
				.updatedAt(invoiceLineDto.getUpdatedAt() != null ? invoiceLineDto.getUpdatedAt() : null).build();
	}

	public InvoiceLineDto mapToInvoiceLineDto(InvoiceLine invoiceLine) {
		if (invoiceLine == null) {
			return null;
		}

		InvoiceLineDto invoiceLineDto = new InvoiceLineDto();

		invoiceLineDto.setId(invoiceLine.getId());
		invoiceLineDto.setInvoiceId(invoiceLine.getInvoiceId());
		invoiceLineDto.setDescription(invoiceLine.getDescription());
		invoiceLineDto.setQuantity(invoiceLine.getQuantity());
		invoiceLineDto.setUnitPrice(invoiceLine.getUnitPrice());
		invoiceLineDto.setTotal(invoiceLine.getTotal());
		invoiceLineDto.setCreatedAt(invoiceLine.getCreatedAt());
		invoiceLineDto.setUpdatedAt(invoiceLine.getUpdatedAt());

		return invoiceLineDto;
	}
}
