package com.alura.forum.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alura.forum.dto.TopicoDTO;
import com.alura.forum.services.TopicoService;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

	@Autowired
	private TopicoService topicoService;
	
	@GetMapping
	public ResponseEntity<List<TopicoDTO>> buscarTopicos() {
		List<TopicoDTO> topicos = topicoService.listarTopicos();
		return ResponseEntity.ok().body(topicos);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<Page<TopicoDTO>> buscarTopicosPaginado(Pageable pageable) {
		Page<TopicoDTO> topicos = topicoService.listarTopicosPaginados(pageable);
		return ResponseEntity.ok().body(topicos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TopicoDTO> buscarTopicoPorId(@PathVariable Long id) {
		TopicoDTO topico = topicoService.buscaTopicoPorId(id);
		return ResponseEntity.ok().body(topico);
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> inserirTopico(@RequestBody TopicoDTO dto) {
		 dto = topicoService.inserirTopico(dto);
		 URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(dto.getId())
				 .toUri(); 
		return ResponseEntity.created(uri).body(dto);	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TopicoDTO> atualizarTopico(@PathVariable Long id, @RequestBody TopicoDTO dto) {
		TopicoDTO topico = topicoService.atualizarTopico(id, dto);
		return ResponseEntity.ok().body(topico);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
		topicoService.excluirTopico(id);
		return ResponseEntity.noContent().build();
	}
}
