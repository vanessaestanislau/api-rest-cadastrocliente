package br.com.builders.cadastro.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;

import br.com.builders.cadastro.model.Cliente;

public class ClienteDto { 

	private Long id;
	private String nome;
	private LocalDateTime cadastro;
	private String cpf;
	private LocalDate nascimento;
	private String telefone;
	private String email;
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cadastro = cliente.getCadastro();
		this.cpf = cliente.getCpf();
		this.nascimento = cliente.getNascimento();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
	}
	
	public String getCpf() {
		return cpf;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDateTime getCadastro() {
		return cadastro;
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

}
