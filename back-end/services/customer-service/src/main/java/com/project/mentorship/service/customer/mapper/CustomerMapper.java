package com.project.mentorship.service.customer.mapper;

import com.project.mentorship.contract.customer.model.CustomerDto;
import com.project.mentorship.service.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public final class CustomerMapper {

	public Customer mapToDomain(CustomerDto customerDto) {
		if (customerDto == null) {
			return null;
		}

		return Customer.builder().id(customerDto.getId() != null ? customerDto.getId() : null)
				.userId(customerDto.getUserId() != null ? customerDto.getUserId() : null)
				.firstName(customerDto.getFirstName() != null ? customerDto.getFirstName() : null)
				.lastName(customerDto.getLastName() != null ? customerDto.getLastName() : null)
				.email(customerDto.getEmail() != null ? customerDto.getEmail() : null)
				.phone(customerDto.getPhone() != null ? customerDto.getPhone() : null)
				.createdAt(customerDto.getCreatedAt() != null ? customerDto.getCreatedAt() : null)
				.updatedAt(customerDto.getUpdatedAt() != null ? customerDto.getUpdatedAt() : null).build();
	}

	public CustomerDto mapToDto(Customer customer) {
		if (customer == null) {
			return null;
		}

		CustomerDto customerDto = new CustomerDto();

		customerDto.setId(customer.getId());
		customerDto.setUserId(customer.getUserId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhone(customer.getPhone());
		customerDto.setCreatedAt(customer.getCreatedAt());
		customerDto.setUpdatedAt(customer.getUpdatedAt());

		return customerDto;
	}
}
