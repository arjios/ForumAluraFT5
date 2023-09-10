package com.alura.forum.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.forum.dto.CursoDTO;
import com.alura.forum.entities.Curso;
import com.alura.forum.repositories.CursoRepository;
import com.alura.forum.services.exceptions.ServiceNotFoundException;

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
	public Page<CursoDTO> buscaCursosPAginados(Pageable pageable) {
		Page<Curso> pages = cursoRepository.findAll(pageable);
		return pages.map(p -> new CursoDTO(p));
	}
	
	@Transactional
	public CursoDTO buscaCursoPorId(Long id) {
		String msgErro = "Erro: Busca do Curso com id " + id + " não encontrado.";
		Curso curso = cursoRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
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
		String msgErro = "Erro: Não existe Curso com id " + id + " para atualizar.";
		Curso curso = cursoRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
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
