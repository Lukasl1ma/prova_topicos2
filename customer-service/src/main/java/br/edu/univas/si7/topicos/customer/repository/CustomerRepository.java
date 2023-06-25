package br.edu.univas.si7.topicos.customer.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.univas.si7.topicos.customer.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
		
}
