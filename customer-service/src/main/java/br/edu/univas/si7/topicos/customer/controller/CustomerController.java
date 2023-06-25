package br.edu.univas.si7.topicos.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.si7.topicos.customer.dto.CustomerDto;
import br.edu.univas.si7.topicos.customer.service.CustomerService;

@RestController

@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDto> getAllProducts() {
		return service.findAll();
	}

	@GetMapping("/{code}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String code) {
		CustomerDto dto = new CustomerDto(service.findById(code));
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody @Valid CustomerDto product) {
		service.createCustomer(product);
	}

	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduct(@RequestBody @Valid CustomerDto dto, @PathVariable String code) {
		service.updateCustomer(service.toEntity(dto), code);
	}

	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable String code) {
		service.deleteCustomer(code);
	}

}
