package com.alura.forum.controllers;

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

import com.alura.forum.dto.UsuarioDTO;
import com.alura.forum.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() {
		List<UsuarioDTO> result = usuarioService.buscarTodosUsuarios();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<Page<UsuarioDTO>> listarodosUsuariosPaginados(Pageable pageable) {
		Page<UsuarioDTO> usuarios = usuarioService.buscarTodosUsuariosPaginados(pageable);
		return  ResponseEntity.ok().body(usuarios);	
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> listarUsuarioPorId(@PathVariable Long id) {
		String msgErro = "Erro: Busca do Usuario com id " + id + " não encontrado.";
		UsuarioDTO dto = usuarioService.buscarUsuarioPorId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		String msgErroInserir = "Erro: Não foi posssivel criar um novo Usuario.";
		usuarioDTO = usuarioService.inserirUsuario(usuarioDTO);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO>  alterarUsuario(@PathVariable Long id, @RequestBody  UsuarioDTO usuarioDTO) {
		String msgErroAtualizar= "Erro: Não foi posssivel alterar o Usuario de id: " + id;
		usuarioDTO = usuarioService.atualizarUsuario(id, usuarioDTO);
		return ResponseEntity.ok().body(usuarioDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletarUsuarioPorId(@PathVariable  Long id) {
		String msgErro = "Erro: Exclusão do Usuario com id " + id + " não encontrado.";
		usuarioService.excluirUsuarioPorId(id);
	}
	
	
}
