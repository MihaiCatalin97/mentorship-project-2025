package com.project.mentorship.service.customer.service;

import com.project.mentorship.service.customer.domain.Customer;
import com.project.mentorship.service.customer.persistence.CustomerRepository;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer create(Customer customer) {
		customer.setId(UUID.randomUUID());
		customer.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
		customer.setUpdatedAt(null);

		return customerRepository.save(customer);
	}
}
