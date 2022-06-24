package br.com.builders.cadastro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.builders.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente> findByNome(String nome, Pageable paginacao); 
	
	List<Cliente> findByCpf(String cpf);
	

}
