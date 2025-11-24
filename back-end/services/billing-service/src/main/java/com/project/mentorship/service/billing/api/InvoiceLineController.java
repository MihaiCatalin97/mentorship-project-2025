package com.project.mentorship.service.billing.api;

import com.project.mentorship.service.billing.api.dto.InvoiceLineDto;
import com.project.mentorship.service.billing.mapper.InvoiceLineMapper;
import com.project.mentorship.service.billing.service.InvoiceLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice-line")
@RequiredArgsConstructor
public class InvoiceLineController {

	private final InvoiceLineService invoiceLineService;
	private final InvoiceLineMapper invoiceLineMapper;
	@PostMapping
	public ResponseEntity<InvoiceLineDto> create(@RequestBody InvoiceLineDto request) {
		var invoiceLine = invoiceLineMapper.mapToInvoiceLine(request);
		var createdInvoiceLine = invoiceLineService.create(invoiceLine);
		var response = invoiceLineMapper.mapToInvoiceLineDto(createdInvoiceLine);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
