package com.project.mentorship.service.billing.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceLineService implements BaseService<InvoiceLine> {

	private final BaseRepository<InvoiceLine> invoiceLineRepository;

	@Override
	public InvoiceLine create(InvoiceLine invoiceLine) {
		return invoiceLineRepository.save(invoiceLine);
	}
}
