package br.com.builders.cadastro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.builders.cadastro.controller.dto.ClienteDto;
import br.com.builders.cadastro.controller.form.AtualizacaoClienteForm;
import br.com.builders.cadastro.controller.form.ClienteForm;
import br.com.builders.cadastro.model.Cliente;
import br.com.builders.cadastro.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public Page<ClienteDto> lista(@RequestParam(required = false) String nome, @RequestParam int pagina,
			@RequestParam int qtd) {

		Pageable paginacao = PageRequest.of(pagina, qtd);

		if (nome == null) {
			Page<Cliente> clientes = clienteRepository.findAll(paginacao);
			return ClienteDto.converter(clientes);
		} else {
			Page<Cliente> clientes = clienteRepository.findByNome(nome, paginacao);
			return ClienteDto.converter(clientes);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter(clienteRepository);
		clienteRepository.save(cliente);

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoClienteForm form) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
