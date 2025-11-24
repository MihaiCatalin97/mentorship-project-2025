package com.project.mentorship.service.customer.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.project.mentorship.service.customer.CustomerServiceApplication;
import com.project.mentorship.service.customer.domain.Customer;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CustomerServiceApplication.class)
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void shouldSaveCustomerAndAddItToList() {
		// Given
		Customer customer = Customer.builder().id(UUID.randomUUID()).firstName("Olivia").lastName("Taylor")
				.email("olivia.taylor@example.com").phone("+40700111222").build();
		// When
		Customer saved = customerRepository.save(customer);

		// Then
		assertNotNull(saved);
		assertNotNull(saved, "Saved customer should not be null");
		assertNotNull(saved.getId(), "ID should be retained");
		assertEquals(customer.getFirstName(), saved.getFirstName());
		assertEquals(customer.getLastName(), saved.getLastName());
		assertEquals(customer.getEmail(), saved.getEmail());
		assertEquals(customer.getPhone(), saved.getPhone());
	}
}
