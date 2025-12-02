package com.project.mentorship.service.customer.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.customer.CustomerServiceApplication;
import com.project.mentorship.service.customer.api.dto.CustomerDto;
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
		CustomerDto customerDto = new CustomerDto(UUID.fromString("11111111-1111-1111-1111-111111111111"),
				UUID.fromString("33333333-3333-3333-3333-333333333333"), "Olivia", "Taylor",
				"olivia.taylor@example.com", "+40700111222", OffsetDateTime.parse("2025-02-10T14:30:00Z"),
				OffsetDateTime.parse("2025-09-20T08:45:00Z"));

		// When
		Customer customer = mapper.mapToDomain(customerDto);

		// Then
		assertNotNull(customer);
		assertEquals(customerDto.userId(), customer.getUserId());
		assertEquals(customerDto.firstName(), customer.getFirstName());
		assertEquals(customerDto.lastName(), customer.getLastName());
		assertEquals(customerDto.email(), customer.getEmail());
		assertEquals(customerDto.phone(), customer.getPhone());
		assertEquals(customerDto.createdAt(), customer.getCreatedAt());
		assertEquals(customerDto.updatedAt(), customer.getUpdatedAt());
	}

	@Test
	void mapToDomain_shouldReturnNull_whenDtoIsNull() {
		Customer customer = mapper.mapToDomain(null);
		assertNull(customer);
	}

	@Test
	void mapToDomain_shouldSetAllFieldsToNull_whenDtoFieldsAreNull() {
		// Given
		CustomerDto customerDto = new CustomerDto(null, null, null, null, null, null, null, null);

		// When
		Customer customer = mapper.mapToDomain(customerDto);

		// Then
		assertNotNull(customer);
		assertNull(customer.getId());
		assertNull(customer.getUserId());
		assertNull(customer.getFirstName());
		assertNull(customer.getLastName());
		assertNull(customer.getEmail());
		assertNull(customer.getPhone());
		assertNull(customer.getCreatedAt());
		assertNull(customer.getUpdatedAt());
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
		assertEquals(customer.getId(), dto.id());
		assertEquals(customer.getUserId(), dto.userId());
		assertEquals(customer.getFirstName(), dto.firstName());
		assertEquals(customer.getLastName(), dto.lastName());
		assertEquals(customer.getEmail(), dto.email());
		assertEquals(customer.getPhone(), dto.phone());
		assertEquals(customer.getCreatedAt(), dto.createdAt());
		assertEquals(customer.getUpdatedAt(), dto.updatedAt());
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
		assertNull(dto.id());
		assertNull(dto.userId());
		assertNull(dto.firstName());
		assertNull(dto.lastName());
		assertNull(dto.email());
		assertNull(dto.phone());
		assertNull(dto.createdAt());
		assertNull(dto.updatedAt());
	}
}