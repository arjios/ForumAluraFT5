package com.alura.forum.dto;

import java.time.LocalDateTime;

import com.alura.forum.entities.Topico;
import com.alura.forum.entities.Usuario;
import com.alura.forum.entities.enuns.StatusTopico;

public class TopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

	private Usuario usuario;

	public TopicoDTO() {
	}

	public TopicoDTO(Long id, String titulo, String mensagem, 
			LocalDateTime dataCriacao, StatusTopico status, 
			Usuario autor) {
		this.id = id;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.dataCriacao = dataCriacao;
		this.status = status;
		this.usuario = autor;
	}

	public TopicoDTO(Topico entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		mensagem = entity.getMensagem();
		dataCriacao = entity.getDataCriacao();
		status = entity.getStatus();
		usuario = entity.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
