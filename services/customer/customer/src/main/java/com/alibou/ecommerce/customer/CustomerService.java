package com.alibou.ecommerce.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibou.ecommerce.exception.CustomerNotFoundException;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final CustomerMapper mapper;

	public String createCustomer(CustomerRequest request) {
		// TODO Auto-generated method stub
		var customer = customerRepository.save(mapper.toCustomer(request));
		return customer.getId();
	}

	public void updateCustomer(@Valid CustomerRequest request) {
		// TODO Auto-generated method stub
		var customer = customerRepository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
				String.format("Cannot Update customer:: No customer found with the provided id's::%s")
				));
	
		mergerCustomer(customer , request);
		customerRepository.save(customer);
	}

	private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(request.firstName())) {
			customer.setFirstName(request.firstName());
		}
		if(StringUtils.isNotBlank(request.lastName())) {
			customer.setLastName(request.lastName());
		}
		if(StringUtils.isNotBlank(request.firstName())) {
			customer.setEmail(request.email());
		}
		if(request.address() != null) {
			customer.setAddress(request.address());
		}
		
	}

	public List<CustomerResponse> findAllCustomers() {
		return customerRepository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
	}

	public Boolean existsById(String customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId).isPresent();
	}

	public CustomerResponse findById(String customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId).map(mapper::fromCustomer)
				.orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the"
						+ "the provided id:: %s ", customerId)));
	}
	

	
	
	
	
	
	
	
	
	
	

}
