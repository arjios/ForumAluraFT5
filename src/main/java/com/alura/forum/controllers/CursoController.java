package com.alura.forum.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.alura.forum.dto.CursoDTO;
import com.alura.forum.services.CursoService;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<CursoDTO>> buscarCursos() {
		List<CursoDTO> cursos = cursoService.buscaTodosCursos();
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CursoDTO> buscarCursoId(@PathVariable Long id) {
		CursoDTO cursoDTO = cursoService.buscaCursoPorId(id);
		return ResponseEntity.ok().body(cursoDTO);
	}
	
	@PostMapping
	public ResponseEntity<CursoDTO> inserirCurso(@RequestBody CursoDTO dto) {
		CursoDTO cursoDTO = cursoService.inserirCurso(dto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cursoDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CursoDTO> atualizarCurso(@PathVariable Long id, @RequestBody CursoDTO dto) {
		CursoDTO cursoDTO = cursoService.atualizarCurso(id, dto);
		return ResponseEntity.ok().body(cursoDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
		cursoService.excluirCurso(id);
		return ResponseEntity.noContent().build();
	}
}
