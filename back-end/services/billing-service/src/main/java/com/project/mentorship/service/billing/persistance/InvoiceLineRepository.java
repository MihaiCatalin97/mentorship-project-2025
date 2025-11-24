package com.project.mentorship.service.billing.persistance;

import com.project.mentorship.lib.pattern.Repository;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@org.springframework.stereotype.Repository
public class InvoiceLineRepository implements Repository<InvoiceLine, UUID> {

	private final List<InvoiceLine> invoiceLines = new CopyOnWriteArrayList<>();

	@Override
	public InvoiceLine save(InvoiceLine entity) {
		if (entity.getId() == null) {
			entity.setId(UUID.randomUUID());
		}
		invoiceLines.add(entity);
		return entity;
	}

}
