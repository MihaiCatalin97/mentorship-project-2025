package com.project.mentorship.service.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.customer.domain.Customer;
import com.project.mentorship.service.customer.persistence.CustomerRepository;
import java.time.ZoneOffset;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	private final Customer customer = new Customer().setId(UUID.randomUUID());

	@Test
	void crete_ShouldSaveAndReturnCustomer() {
		// Given
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);

		// When
		Customer created = customerService.create(customer);

		// Then
		assertNotNull(created);
		assertEquals(customer.getId(), created.getId());
		assertNotNull(customer.getCreatedAt());
		assertNull(customer.getUpdatedAt());
		assertEquals(ZoneOffset.UTC, customer.getCreatedAt().getOffset());
		verify(customerRepository, times(1)).save(customer);
	}
}
