package br.edu.univas.si7.topicos.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.topicos.customer.entity.CustomerEntity;
import br.edu.univas.si7.topicos.customer.entity.CustomerType;
import br.edu.univas.si7.topicos.customer.repository.CustomerRepository;

@SpringBootApplication
public class CustomerApp implements CommandLineRunner {

	@Autowired
	private CustomerRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		CustomerEntity customer01 = new CustomerEntity("1", "Cliente 01", "cliente01@email.com", 
				"99999-1111", CustomerType.PESSOAFISICA);
		CustomerEntity customer02 = new CustomerEntity("2", "Cliente 02", "cliente02@email.com", 
				"99999-2222", CustomerType.PESSOAJURIDICA);
		repo.save(customer01);
		repo.save(customer02);
	}

}
