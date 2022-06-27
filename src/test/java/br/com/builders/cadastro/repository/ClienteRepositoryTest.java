package br.com.builders.cadastro.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.builders.cadastro.model.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository repository;
	
	@Test
	public void deveriaCarregarUmClienteAoBuscarPeloCpf() {
		
		String cpf = "09061646978";				
		Optional<Cliente> cliente = repository.findByCpf(cpf);
		Assert.assertNotNull(cliente);
		Assert.assertEquals(cpf, cliente.get().getCpf());	
	}
	
	@Test
	public void naoDeveriaCarregarUmClienteCujoCpfNaoEstejaCadastrado() {
		
		String cpf = "11111111111";				
		Optional<Cliente> cliente = repository.findByCpf(cpf);
		Assert.assertFalse(cliente.isPresent());
	}

}
