package br.edu.univas.si7.topicos.customer.dto;

import br.edu.univas.si7.topicos.customer.entity.CustomerEntity;
import br.edu.univas.si7.topicos.customer.entity.CustomerType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

	private String id;
	private String name;
	private String email;
	private String phoneNumber;
	private CustomerType type;

	public CustomerDto(CustomerEntity customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.email = customer.getEmail();
		this.phoneNumber = customer.getPhoneNumber();
		this.type = customer.getType();
	}
}
