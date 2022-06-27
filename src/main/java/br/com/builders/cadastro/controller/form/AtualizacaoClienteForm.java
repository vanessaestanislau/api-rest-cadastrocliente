package br.com.builders.cadastro.controller.form;

import javax.validation.constraints.Email;

import br.com.builders.cadastro.model.Cliente;
import br.com.builders.cadastro.repository.ClienteRepository;

public class AtualizacaoClienteForm {

	private String telefone;

	@Email
	private String email;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// busca tópicos pelo id do BD e atualiza informações que foram modificadas
	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getReferenceById(id);
		if (this.telefone != null && !this.telefone.trim().equals("")) {
			cliente.setTelefone(this.telefone);
		}
		if (this.email != null && !this.email.trim().equals("")) {
			cliente.setEmail(this.email);
		}
		cliente = clienteRepository.save(cliente);
		return cliente;

	}

}
