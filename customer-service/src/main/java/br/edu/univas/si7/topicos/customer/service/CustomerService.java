package br.edu.univas.si7.topicos.customer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.topicos.customer.dto.CustomerDto;
import br.edu.univas.si7.topicos.customer.entity.CustomerEntity;
import br.edu.univas.si7.topicos.customer.exception.CustomerException;
import br.edu.univas.si7.topicos.customer.exception.ObjectNotFoundException;
import br.edu.univas.si7.topicos.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repo;

	@Autowired
	public CustomerService(CustomerRepository repo) {
		this.repo = repo;
	}

	public List<CustomerDto> findAll() {
		return repo.findAll().stream().map(p -> new CustomerDto(p)).collect(Collectors.toList());
	}

	public CustomerEntity findById(String id) {
		Optional<CustomerEntity> obj = repo.findById(id);
		CustomerEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Customer " + id + " not found"));
		return entity;
	}

	public void createCustomer(CustomerDto customer) {
		repo.save(toEntity(customer));
	}

	public CustomerEntity toEntity(CustomerDto cust) {
		return new CustomerEntity(cust.getId(), cust.getName(), cust.getEmail(), cust.getPhoneNumber(), cust.getType());
	}

	public void updateCustomer(CustomerEntity customer, String id) {
		if (id == null || customer == null || !id.equals(customer.getId())) {
			throw new CustomerException("Invalid customer id.");
		}
		CustomerEntity existingObj = findById(id);
		updateData(existingObj, customer);
		repo.save(existingObj);
	}

	private void updateData(CustomerEntity existingObj, CustomerEntity obj) {
		existingObj.setName(obj.getName());
		existingObj.setEmail(obj.getEmail());
		existingObj.setPhoneNumber(obj.getPhoneNumber());
		existingObj.setType(obj.getType());
	}

	public void deleteCustomer(String id) {
		if (id == null) {
			throw new CustomerException("Customer id can not be null.");
		}

		CustomerEntity obj = findById(id);
		repo.delete(obj);

	}

}
