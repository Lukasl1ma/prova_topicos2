package br.edu.univas.si7.topicos.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data

public class CustomerEntity {

	public CustomerEntity(String id2, String name2, String email2, String phoneNumber2, CustomerType type2) {
		super();
		this.id = id2;
		this.name = name2;
		this.email = email2;
		this.phoneNumber = phoneNumber2;
		this.type = type2;
	}

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "nome")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String phoneNumber;
	
	@Column(name = "tipo")
	private CustomerType  type;
}
