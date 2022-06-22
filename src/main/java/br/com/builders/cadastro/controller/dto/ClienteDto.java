package br.com.builders.cadastro.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.builders.cadastro.model.Cliente;

public class ClienteDto { 

	private Long id;
	private String nome;
	private LocalDate dataCadastro;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.dataCadastro = cliente.getDataCadastro();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

}
