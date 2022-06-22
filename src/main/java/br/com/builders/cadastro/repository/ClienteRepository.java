package br.com.builders.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.builders.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome); 
	
	List<Cliente> findByCpf(String cpf);
	

}
