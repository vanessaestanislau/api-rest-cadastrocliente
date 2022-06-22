package br.com.builders.cadastro.controller.form;

import java.time.LocalDate;

import br.com.builders.cadastro.model.Cliente;
import br.com.builders.cadastro.repository.ClienteRepository;

public class ClienteForm { //aqui defino quais campos irão chegar do cliente

	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

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

	//mét para converter dados que chegam no form em um objeto tipo cliente
	public Cliente converter(ClienteRepository clienteRepository) { 	
	//	Cliente cliente = clienteRepository.findByNome(nome);
		return new Cliente(nome, cpf, dataNascimento, telefone, email);
	}

}
