package com.project.mentorship.service.billing.api;

import com.project.mentorship.contract.billing.api.BillingApiDelegate;
import com.project.mentorship.contract.billing.model.InvoiceLineDto;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import com.project.mentorship.service.billing.mapper.InvoiceLineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceLineApiDelegateImpl implements BillingApiDelegate {

	private final BaseService<InvoiceLine> invoiceLineService;
	private final InvoiceLineMapper invoiceLineMapper;

	@Override
	public ResponseEntity<InvoiceLineDto> createInvoiceLine(InvoiceLineDto request) {

		InvoiceLine invoiceLine = invoiceLineMapper.mapToInvoiceLine(request);
		InvoiceLine createdInvoiceLine = invoiceLineService.create(invoiceLine);
		InvoiceLineDto response = invoiceLineMapper.mapToInvoiceLineDto(createdInvoiceLine);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
