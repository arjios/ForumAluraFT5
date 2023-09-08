package com.alura.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forum.dto.CursoDTO;
import com.alura.forum.services.CursoService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	@Transactional
	public List<CursoDTO> buscarCursos() {
		return cursoService.buscaTodosCursos();
	}
	
	@GetMapping("/{id}")
	@Transactional
	public CursoDTO buscarCursoId(@PathVariable Long id) {
		return cursoService.buscaCursoPorId(id);
	}
	
	@Transactional
	@PostMapping
	public CursoDTO inserirCurso(@RequestBody CursoDTO dto) {
		return cursoService.inserirCurso(dto);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public CursoDTO inserirCurso(@PathVariable Long id, @RequestBody CursoDTO dto) {
		return cursoService.atulizarCurso(id, dto);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public void deletarCurso(@PathVariable Long id) {
		cursoService.excluirCurso(id);
	}
}
