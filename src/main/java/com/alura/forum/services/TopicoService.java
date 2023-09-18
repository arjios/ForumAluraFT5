package com.alura.forum.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.forum.dto.TopicoDTO;
import com.alura.forum.entities.Topico;
import com.alura.forum.repositories.TopicoRepository;
import com.alura.forum.services.exceptions.ServiceDatabaseException;
import com.alura.forum.services.exceptions.ServiceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TopicoService {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Transactional
	public List<TopicoDTO> listarTopicos() {
		List<Topico> topicos = new ArrayList<>();
		topicos = topicoRepository.findAll();
		return topicos.stream().map(t -> new TopicoDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional
	public Page<TopicoDTO> listarTopicosPaginados(Pageable pageable) {
		Page<Topico> pages = topicoRepository.findAll(pageable);
		return pages.map(p -> new TopicoDTO(p));
	}

	@Transactional
	public TopicoDTO buscaTopicoPorId(Long id) {
		String msgErro = "Erro: Busca do Topico com id " + id + " não encontrado.";
		Topico topico = topicoRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		return new TopicoDTO(topico);
	}
	
	@Transactional
	public TopicoDTO inserirTopico(TopicoDTO dto) {
		Topico topico = new Topico();
		topico = topicoRepository.save(copyDTO(dto,topico));
		return new TopicoDTO(topico);		
	}
	
	@Transactional
	public TopicoDTO atualizarTopico(Long id, TopicoDTO dto) {
		String msgErro = "Erro: Não existe Topico com id " + id + " para atualizar.";
		Topico topico = new Topico();
		topico = topicoRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		topico.setMensagem(dto.getMensagem());
		topico.setStatus(dto.getStatus());
		topico = topicoRepository.save(topico);
		return new TopicoDTO(topico);
	}
	
	public void excluirTopico(Long id) {
		String msgErro = "Erro: Não existe Usuario com id " + id + " para excluir.";
		String msgData = "Erro: Violação de integridade para o " + id + " na exclusão.";
		if(!topicoRepository.existsById(id)) {
			throw new ServiceNotFoundException(msgErro);
		}
		try {
			topicoRepository.deleteById(id);			
		} catch(DataIntegrityViolationException dive) {
			throw new ServiceDatabaseException(msgData);
		}

	}
	
	private Topico copyDTO(TopicoDTO dto, Topico entity) {
//		String msgErroUsuario = "Erro: Busca do Autor na inserção do topico com id " 
//				+ dto.getUsuario().getId()
//				+ " não encontrado.";
//		String msgErroCurso = "Erro: Busca do Curso na inserção do topico com id " 
//				+ dto.getUsuario().getId()
//				+ " não encontrado.";
		entity.setTitulo(dto.getTitulo());
		entity.setMensagem(dto.getMensagem());
		entity.setDataCriacao(LocalDateTime.now());
		entity.setStatus(dto.getStatus());
//		Curso curso = cursoRepository
//				.findById(dto.getCurso().getId())
//				.orElseThrow(() -> new ServiceNotFoundException(msgErroCurso));
//		Usuario autor = usuarioRepository
//				.findById(dto.getUsuario().getId())
//				.orElseThrow(() -> new ServiceNotFoundException(msgErroUsuario));
		return entity;
	}
}
