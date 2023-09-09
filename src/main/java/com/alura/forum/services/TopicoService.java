package com.alura.forum.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.forum.dto.TopicoDTO;
import com.alura.forum.entities.Topico;
import com.alura.forum.repositories.TopicoRepository;

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
		Topico topico = topicoRepository.findById(id).get();
		return new TopicoDTO(topico);
	}
	
	@Transactional
	public TopicoDTO inserirTopico(TopicoDTO dto) {
		Topico topico = new Topico();
		topico.setTitulo(dto.getTitulo());
		topico.setMensagem(dto.getMensagem());
		topico.setDataCriacao(LocalDateTime.now());
		topico.setStatus(dto.getStatus());
		topico.setAutor(dto.getAutor());
		topico = topicoRepository.save(topico);
		return new TopicoDTO(topico);		
	}
	
	@Transactional
	public TopicoDTO atualizarTopico(Long id, TopicoDTO dto) {
		Topico topico = new Topico();
		topico = topicoRepository.findById(id).get();
		topico.setMensagem(dto.getMensagem());
		topico.setStatus(dto.getStatus());
		topico = topicoRepository.save(topico);
		return new TopicoDTO(topico);
	}
	
	@Transactional
	public void excluirTopico(Long id) {
		topicoRepository.deleteById(id);
	}
	
}
