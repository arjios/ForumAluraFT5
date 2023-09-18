package com.alura.forum.services;


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
	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public List<CursoDTO> buscarTodosCursos() {
		List<Curso> cursos = cursoRepository.findAll();
		return cursos.stream().map(c -> new CursoDTO(c)).collect(Collectors.toList());
		
	}
	
	@Transactional
	public Page<CursoDTO> buscarTodosCursosPaginados(Pageable pageable) {
		Page<Curso> cursos = cursoRepository.findAll(pageable);
		return cursos.map(c -> new CursoDTO(c));	
	}
	
	@Transactional
	public CursoDTO buscarCursoPorId(Long id) {
		String msgErro = "Erro: Busca do Curso com id " + id + " não encontrado.";
		Curso curso = cursoRepository.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
//		return new CursoDTO(curso, curso.getUsuarios());
		return new CursoDTO(curso);
	}
	
	@Transactional
	public CursoDTO inserirCurso(CursoDTO cursoDTO) {
//		String msgErroInserir = "Erro: Curso já existe.";
		Curso curso = new Curso();
		curso.setNome(cursoDTO.getNome());
		curso.setCategoria(cursoDTO.getCategoria());
		curso = cursoRepository.save(curso);
		return new CursoDTO(curso);	
	}
	
//	@Transactional
//	public CursoDTO inserirUsuarioCurso(Long id, CursoDTO cursoDTO) {
//		Curso curso = new Curso();
//		curso = cursoRepository
//				.getReferenceById(id);
//		for (UsuarioDTO user : cursoDTO.getUsuarios()) {
//			Usuario usuario = usuarioRepository
//					.getReferenceById(user.getId());
//			curso.getUsuarios().add(usuario);
//		}
//		curso = cursoRepository.save(curso);
//		return new CursoDTO(curso);	
//	}
	
	@Transactional
	public CursoDTO atualizarCurso(Long id, CursoDTO cursoDTO) {
		String msgErroAtualizar= "Erro: Não foi posssivel atualizar o Curso de id: " + id;
		Curso curso = new Curso();
		curso = cursoRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErroAtualizar));
		curso.setNome(cursoDTO.getNome());
		curso.setCategoria(cursoDTO.getCategoria());
		curso = cursoRepository.save(curso);
		return new CursoDTO(curso);	
	}
	
	public void excluirCursoPorId(Long id) {
		String msgErro = "Erro: Exclusão do Curso com id " + id + " não encontrado.";
		if(cursoRepository.getReferenceById(id) == null) {
			throw new ServiceNotFoundException(msgErro);
		}
		cursoRepository.deleteById(id);
	}
}
