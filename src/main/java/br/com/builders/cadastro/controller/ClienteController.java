package br.com.builders.cadastro.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.builders.cadastro.controller.dto.ClienteDto;
import br.com.builders.cadastro.controller.form.ClienteForm;
import br.com.builders.cadastro.model.Cliente;
import br.com.builders.cadastro.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired 
	private ClienteRepository clienteRepository;
	//service?
	
	// todos os verbos http:
	@GetMapping
	public List<ClienteDto> lista(String nome) { 
		if (nome == null) { // sem filtro: chamo findAll
			List<Cliente> clientes = clienteRepository.findAll();
			return ClienteDto.converter(clientes);
		} else {  
			List<Cliente> clientes = clienteRepository.findByNome(nome);
			return ClienteDto.converter(clientes);
		}
	}	
			
    @PostMapping
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody ClienteForm form, UriComponentsBuilder uriBuilder) {
    	Cliente cliente = form.converter(clienteRepository);
    	clienteRepository.save(cliente);
    	
    	URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
    	return ResponseEntity.created(uri).body(new ClienteDto(cliente)); 	
    }
    	
	
}	
		



