package br.edu.univas.si7.topicos.customer.entity;

import java.util.stream.Stream;

import br.edu.univas.si7.topicos.customer.exception.CustomerException;

public enum CustomerType {
	PESSOAFISICA(0), PESSOAJURIDICA(1);

	private int code;

	private CustomerType (int code) {
	this.code = code;
	}

	
	public static CustomerType getUnit(Integer code) {
		
		return Stream.of(CustomerType.values())
				.filter(t -> t.getCode() == code)
				.findFirst()
				.orElseThrow(() -> new CustomerException("Invalid unit: " +

						code));
	}

	public int getCode() {
	return code;

	
	}
}


