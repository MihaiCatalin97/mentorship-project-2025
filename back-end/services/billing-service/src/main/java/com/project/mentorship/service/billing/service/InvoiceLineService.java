package com.project.mentorship.service.billing.service;

import com.project.mentorship.service.billing.domain.InvoiceLine;
import com.project.mentorship.service.billing.persistance.InvoiceLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceLineService {

	private final InvoiceLineRepository invoiceLineRepository;

	public InvoiceLine create(InvoiceLine invoiceLine) {
		return invoiceLineRepository.save(invoiceLine);
	}
}
