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

import com.alura.forum.dto.UsuarioDTO;
import com.alura.forum.dto.UsuarioNovoDTO;
import com.alura.forum.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
		List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<Page<UsuarioDTO>> buscarUsuariosPaginado(Pageable pageable) {
		Page<UsuarioDTO> pages = usuarioService.listarUsuarioPaginados(pageable);
		return ResponseEntity.ok().body(pages);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
		UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioNovoDTO> inserirUsuario(@RequestBody UsuarioNovoDTO dto) {
		dto = usuarioService.inserirUsuario(dto);
		URI uri = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(dto.getId())
				 .toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> alterarUsuario(@PathVariable Long id,
			@RequestBody UsuarioNovoDTO dto) {
		UsuarioDTO usuario = usuarioService.atualizarUsuario(id, dto);
		return ResponseEntity.ok().body(usuario);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		usuarioService.excluirUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
