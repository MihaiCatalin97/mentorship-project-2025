package com.project.mentorship.service.billing.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceLineRepository implements BaseRepository<InvoiceLine> {

	private final List<InvoiceLine> invoiceLines = new ArrayList<>();

	@Override
	public InvoiceLine save(InvoiceLine entity) {
		invoiceLines.add(entity);
		return entity;
	}

}
