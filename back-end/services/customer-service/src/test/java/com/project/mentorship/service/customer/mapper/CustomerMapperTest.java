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
public class CustomerMapperTest {
	private final CustomerMapper mapper = new CustomerMapper();

	@Test
	void mapToDomain_shouldMapAllFields_whenAllPresent() {
		// Given
		CustomerDto customerDto = new CustomerDto(UUID.fromString("11111111-1111-1111-1111-111111111111"),
				UUID.fromString("33333333-3333-3333-3333-333333333333"), "Olivia", "Taylor",
				"olivia.taylor@example.com", "+40700111222", OffsetDateTime.parse("2025-02-10T14:30:00Z"),
				OffsetDateTime.parse("2025-09-20T08:45:00Z"));

		Customer customer = mapper.mapToDomain(customerDto);

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

}
