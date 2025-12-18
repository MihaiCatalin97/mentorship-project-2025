package com.project.mentorship.service.customer.mapper;

import com.project.mentorship.contract.customer.model.CustomerDto;
import com.project.mentorship.service.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public final class CustomerMapper {

	public Customer mapToDomain(CustomerDto dto) {
		if (dto == null) {
			return null;
		}

		return Customer.builder().id(dto.getId() != null ? dto.getId() : null)
				.userId(dto.getUserId() != null ? dto.getUserId() : null)
				.firstName(dto.getFirstName() != null ? dto.getFirstName() : null)
				.lastName(dto.getLastName() != null ? dto.getLastName() : null)
				.email(dto.getEmail() != null ? dto.getEmail() : null)
				.phone(dto.getPhone() != null ? dto.getPhone() : null)
				.createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : null)
				.updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : null).build();
	}

	public CustomerDto mapToDto(Customer customer) {
		if (customer == null) {
			return null;
		}

		CustomerDto dto = new CustomerDto();

		dto.setId(customer.getId());
		dto.setUserId(customer.getUserId());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setEmail(customer.getEmail());
		dto.setPhone(customer.getPhone());
		dto.setCreatedAt(customer.getCreatedAt());
		dto.setUpdatedAt(customer.getUpdatedAt());

		return dto;
	}
}
