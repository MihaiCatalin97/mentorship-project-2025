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
		CustomerDto dto = mapper.mapToDto(customer);

		// Then
		assertNotNull(dto);
		assertEquals(customer.getId(), dto.getId());
		assertEquals(customer.getUserId(), dto.getUserId());
		assertEquals(customer.getFirstName(), dto.getFirstName());
		assertEquals(customer.getLastName(), dto.getLastName());
		assertEquals(customer.getEmail(), dto.getEmail());
		assertEquals(customer.getPhone(), dto.getPhone());
		assertEquals(customer.getCreatedAt(), dto.getCreatedAt());
		assertEquals(customer.getUpdatedAt(), dto.getUpdatedAt());
	}

	@Test
	void mapToDto_shouldReturnNull_whenCustomerIsNull() {
		// When
		CustomerDto dto = mapper.mapToDto(null);

		// Then
		assertNull(dto);
	}

	@Test
	void mapToDto_shouldSetAllFieldsToNull_whenCustomerFieldsAreNull() {
		// Given
		Customer customer = new Customer();

		// When
		CustomerDto dto = mapper.mapToDto(customer);

		// Then
		assertNotNull(dto);
		assertNull(dto.getId());
		assertNull(dto.getUserId());
		assertNull(dto.getFirstName());
		assertNull(dto.getLastName());
		assertNull(dto.getEmail());
		assertNull(dto.getPhone());
		assertNull(dto.getCreatedAt());
		assertNull(dto.getUpdatedAt());
	}
}