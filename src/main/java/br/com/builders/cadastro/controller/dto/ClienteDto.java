package br.com.builders.cadastro.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.data.domain.Page;

import br.com.builders.cadastro.model.Cliente;

public class ClienteDto { 

	private Long id;
	private String nome;
	private LocalDateTime cadastro;
	private String cpf;
	private LocalDate nascimento;
	private String telefone;
	private String email;
	private Integer idade;
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cadastro = cliente.getCadastro();
		this.cpf = cliente.getCpf();
		this.nascimento = cliente.getNascimento();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.idade = Period.between(nascimento, LocalDate.now()).getYears();
	}
	
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
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

	public static Page<ClienteDto> converter(Page<Cliente> clientes) {	
		return clientes.map(ClienteDto::new);
	}

}
