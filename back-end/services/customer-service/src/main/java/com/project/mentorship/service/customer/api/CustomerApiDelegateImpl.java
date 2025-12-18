package com.project.mentorship.service.customer.api;

import com.project.mentorship.contract.customer.api.CustomersApiDelegate;
import com.project.mentorship.contract.customer.model.CustomerDto;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.customer.domain.Customer;
import com.project.mentorship.service.customer.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerApiDelegateImpl implements CustomersApiDelegate {

	private final BaseService<Customer> service;
	private final CustomerMapper mapper;

	@Override
	public ResponseEntity<CustomerDto> createCustomer(CustomerDto request) {
		Customer customer = mapper.mapToDomain(request);
		Customer created = service.create(customer);
		CustomerDto response = mapper.mapToDto(created);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
