package com.project.mentorship.service.billing.domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class InvoiceLine {
	private UUID id;
	private UUID invoiceId;
	private String description;
	private Integer quantity;
	private Double unitPrice;
	private Double total;
}
