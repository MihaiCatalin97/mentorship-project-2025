package com.project.mentorship.service.customer.persistence;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.customer.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository implements BaseRepository<Customer> {

	private final List<Customer> customers = new ArrayList<>();

	@Override
	public Customer save(Customer customer) {
		customers.add(customer);
		return customer;
	}
}
