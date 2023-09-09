package com.alura.forum.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.forum.dto.CursoDTO;
import com.alura.forum.entities.Curso;
import com.alura.forum.repositories.CursoRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Transactional
	public List<CursoDTO> buscaTodosCursos() {
		List<Curso> cursos =  new ArrayList<>();
		cursos = cursoRepository.findAll();
		return cursos.stream().map(c -> new CursoDTO(c)).collect(Collectors.toList());
	}
	
	@Transactional
	public CursoDTO buscaCursoPorId(Long id) {
		Curso curso = cursoRepository.findById(id).get();
		return new CursoDTO(curso);
	}
	
	@Transactional
	public CursoDTO inserirCurso(CursoDTO dto) {
		Curso curso = new Curso();
		curso.setCategoria(dto.getCategoria());
		curso.setNome(dto.getNome());
		curso = cursoRepository.save(curso);
		return new CursoDTO(curso);
	}	
	
	@Transactional
	public CursoDTO atualizarCurso(Long id, CursoDTO dto) {
		Curso curso = cursoRepository.findById(id).get();
		curso.setCategoria(dto.getCategoria());
		curso.setNome(dto.getNome());
		curso = cursoRepository.save(curso);
		return new CursoDTO(curso);
	}	
	
	@Transactional
	public void excluirCurso(Long id) {
		cursoRepository.deleteById(id);
	}	
}
