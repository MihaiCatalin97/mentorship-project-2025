package com.project.mentorship.service.billing.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.billing.domain.InvoiceLine;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceLineRepository implements BaseRepository<InvoiceLine> {

	private final List<InvoiceLine> invoiceLines = new ArrayList<>();

	@Override
	public InvoiceLine save(InvoiceLine entity) {
		if (entity.getId() == null) {
			entity.setId(UUID.randomUUID());
		}
		invoiceLines.add(entity);
		return entity;
	}

}
