package com.alibou.ecommerce.customer;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class CustomerMapper {

	public Customer toCustomer(CustomerRequest request) {
		// TODO Auto-generated method stub
		if(request == null) {
			return null;
		}
		return Customer.builder().id(request.id()).firstName(request.firstName()).lastName(request.lastName()).address(request.address())
				.email(request.email()).build();
	}
	
	public CustomerResponse fromCustomer(Customer customer) {
		return new CustomerResponse(
				
				customer.getId(),
				customer.getFirstName(),
				customer.getLastName(),
				customer.getEmail(),
				customer.getAddress()
				);
	}

}
