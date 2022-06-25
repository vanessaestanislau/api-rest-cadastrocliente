package br.com.builders.cadastro.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.builders.cadastro.model.Cliente;
import br.com.builders.cadastro.repository.ClienteRepository;

public class ClienteForm { 
	
	@NotBlank 
	private String nome;
	
	@NotBlank 
	private String cpf;
	
	@NotNull 
	private LocalDate nascimento;
	
	@NotBlank 
	private String telefone;
	
	@NotBlank @Email
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

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
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

	public Cliente converter(ClienteRepository clienteRepository) { 	
		return new Cliente(nome, cpf, nascimento, telefone, email);
	}

}
