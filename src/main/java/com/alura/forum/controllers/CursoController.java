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

import com.alura.forum.dto.CursoDTO;
import com.alura.forum.services.CursoService;


@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<CursoDTO>> listarTodosUsuarios() {
		List<CursoDTO> result = cursoService.buscarTodosCursos();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/pages")
	public ResponseEntity<Page<CursoDTO>> listarodosCursosPaginados(Pageable pageable) {
		Page<CursoDTO> cursos = cursoService.buscarTodosCursosPaginados(pageable);
		return  ResponseEntity.ok().body(cursos);	
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CursoDTO> listarCursoPorId(@PathVariable Long id) {
		String msgErro = "Erro: Busca do Curso com id " + id + " não encontrado.";
		CursoDTO dto = cursoService.buscarCursoPorId(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<CursoDTO> createCurso(@RequestBody CursoDTO cursoDTO) {
		String msgErroInserir = "Erro: Não foi posssivel criar um novo Curso.";
		cursoDTO = cursoService.inserirCurso(cursoDTO);
		return ResponseEntity.ok().body(cursoDTO);
	}
	
//	@PutMapping(value = "/usuario/{id}")
//	public ResponseEntity<CursoDTO> inserirUsuarioCurso(@PathVariable Long id, @RequestBody  CursoDTO cursoDTO){
//		String msgErroAtualizar= "Erro: Não foi posssivel alterar o Curso de id: " + id;
//		cursoDTO = cursoService.inserirUsuarioCurso(id, cursoDTO);
//		return ResponseEntity.ok().body(cursoDTO);
//	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CursoDTO>  alterarCurso(@PathVariable Long id, @RequestBody  CursoDTO cursoDTO) {
		String msgErroAtualizar= "Erro: Não foi posssivel alterar o Curso de id: " + id;
		cursoDTO = cursoService.atualizarCurso(id, cursoDTO);
		return ResponseEntity.ok().body(cursoDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletarCursoPorId(@PathVariable  Long id) {
		String msgErro = "Erro: Exclusão do Curso com id " + id + " não encontrado.";
		cursoService.excluirCursoPorId(id);
	}
}
