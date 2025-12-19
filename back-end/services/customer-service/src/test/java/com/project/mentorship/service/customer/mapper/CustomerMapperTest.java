package com.project.mentorship.service.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.contract.customer.model.CustomerDto;
import com.project.mentorship.service.customer.CustomerServiceApplication;
import com.project.mentorship.service.customer.domain.Customer;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CustomerServiceApplication.class)
class CustomerMapperTest {

	private final CustomerMapper mapper = new CustomerMapper();

	@Test
	void mapToDomain_shouldMapAllFields_whenAllPresent() {
		// Given
		CustomerDto customerDto = new CustomerDto("Olivia", "Taylor", "olivia.taylor@example.com", "+40700111222");
		customerDto.setUserId(UUID.fromString("33333333-3333-3333-3333-333333333333"));
		customerDto.setCreatedAt(OffsetDateTime.parse("2025-02-10T14:30:00Z"));
		customerDto.setUpdatedAt(OffsetDateTime.parse("2025-09-20T08:45:00Z"));

		// When
		Customer customer = mapper.mapToDomain(customerDto);

		// Then
		assertNotNull(customer);
		assertEquals(customerDto.getFirstName(), customer.getFirstName());
		assertEquals(customerDto.getLastName(), customer.getLastName());
		assertEquals(customerDto.getEmail(), customer.getEmail());
		assertEquals(customerDto.getPhone(), customer.getPhone());
		assertEquals(customerDto.getCreatedAt(), customer.getCreatedAt());
		assertEquals(customerDto.getUpdatedAt(), customer.getUpdatedAt());
	}

	@Test
	void mapToDomain_shouldReturnNull_whenDtoIsNull() {
		Customer customer = mapper.mapToDomain(null);
		assertNull(customer);
	}

	@Test
	void mapToDomain_shouldSetAllFieldsToNull_whenDtoFieldsAreNull() {
		// Given
		CustomerDto customerDto = new CustomerDto(null, null, null, null);

		// When
		Customer customer = mapper.mapToDomain(customerDto);

		// Then
		assertNotNull(customer);
		assertNull(customer.getFirstName());
		assertNull(customer.getLastName());
		assertNull(customer.getEmail());
		assertNull(customer.getPhone());
	}

	@Test
	void mapToDto_shouldMapAllFields_whenCustomerHasAllFieldsSet() {
		// Given
		Customer customer = Customer.builder().id(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))
				.userId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")).firstName("Daniel").lastName("Clark")
				.email("daniel.clark@example.com").phone("+12025550123")
				.createdAt(OffsetDateTime.parse("2025-01-15T10:00:00Z"))
				.updatedAt(OffsetDateTime.parse("2025-01-15T12:00:00Z")).build();

		// When
		CustomerDto customerDto = mapper.mapToDto(customer);

		// Then
		assertNotNull(customerDto);
		assertEquals(customer.getId(), customerDto.getId());
		assertEquals(customer.getUserId(), customerDto.getUserId());
		assertEquals(customer.getFirstName(), customerDto.getFirstName());
		assertEquals(customer.getLastName(), customerDto.getLastName());
		assertEquals(customer.getEmail(), customerDto.getEmail());
		assertEquals(customer.getPhone(), customerDto.getPhone());
		assertEquals(customer.getCreatedAt(), customerDto.getCreatedAt());
		assertEquals(customer.getUpdatedAt(), customerDto.getUpdatedAt());
	}

	@Test
	void mapToDto_shouldReturnNull_whenCustomerIsNull() {
		// When
		CustomerDto customerDto = mapper.mapToDto(null);

		// Then
		assertNull(customerDto);
	}

	@Test
	void mapToDto_shouldSetAllFieldsToNull_whenCustomerFieldsAreNull() {
		// Given
		Customer customer = new Customer();

		// When
		CustomerDto customerDto = mapper.mapToDto(customer);

		// Then
		assertNotNull(customerDto);
		assertNull(customerDto.getId());
		assertNull(customerDto.getUserId());
		assertNull(customerDto.getFirstName());
		assertNull(customerDto.getLastName());
		assertNull(customerDto.getEmail());
		assertNull(customerDto.getPhone());
		assertNull(customerDto.getCreatedAt());
		assertNull(customerDto.getUpdatedAt());
	}
}
