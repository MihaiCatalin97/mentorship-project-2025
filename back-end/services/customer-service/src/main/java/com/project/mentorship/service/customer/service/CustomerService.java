package com.project.mentorship.service.customer.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.customer.domain.Customer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements BaseService<Customer> {

	private final BaseRepository<Customer> customerRepository;

    @Override
	public Customer create(Customer customer) {
		customer.setId(UUID.randomUUID());
		customer.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
		customer.setUpdatedAt(null);

		return customerRepository.save(customer);
	}
}
