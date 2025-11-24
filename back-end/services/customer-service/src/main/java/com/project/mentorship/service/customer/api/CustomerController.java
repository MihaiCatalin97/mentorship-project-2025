package com.project.mentorship.service.customer.api;

import com.project.mentorship.service.customer.api.dto.CustomerDto;
import com.project.mentorship.service.customer.domain.Customer;
import com.project.mentorship.service.customer.mapper.CustomerMapper;
import com.project.mentorship.service.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService service;
	private final CustomerMapper mapper;

	@PostMapping
	public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto request) {
		Customer customer = mapper.mapToDomain(request);
		Customer created = service.create(customer);
		CustomerDto response = mapper.mapToDto(created);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
