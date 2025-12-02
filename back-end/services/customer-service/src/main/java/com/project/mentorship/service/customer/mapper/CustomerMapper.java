package com.project.mentorship.service.customer.mapper;

import com.project.mentorship.service.customer.api.dto.CustomerDto;
import com.project.mentorship.service.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public final class CustomerMapper {

	public Customer mapToDomain(CustomerDto dto) {
		if (dto == null) {
			return null;
		}

		return Customer.builder().id(dto.id() != null ? dto.id() : null)
				.userId(dto.userId() != null ? dto.userId() : null)
				.firstName(dto.firstName() != null ? dto.firstName() : null)
				.lastName(dto.lastName() != null ? dto.lastName() : null)
				.email(dto.email() != null ? dto.email() : null).phone(dto.phone() != null ? dto.phone() : null)
				.createdAt(dto.createdAt() != null ? dto.createdAt() : null)
				.updatedAt(dto.updatedAt() != null ? dto.updatedAt() : null).build();
	}

	public CustomerDto mapToDto(Customer customer) {
		if (customer == null) {
			return null;
		}

		return new CustomerDto(customer.getId() != null ? customer.getId() : null,
				customer.getUserId() != null ? customer.getUserId() : null,
				customer.getFirstName() != null ? customer.getFirstName() : null,
				customer.getLastName() != null ? customer.getLastName() : null,
				customer.getEmail() != null ? customer.getEmail() : null,
				customer.getPhone() != null ? customer.getPhone() : null,
				customer.getCreatedAt() != null ? customer.getCreatedAt() : null,
				customer.getUpdatedAt() != null ? customer.getUpdatedAt() : null);
	}
}
