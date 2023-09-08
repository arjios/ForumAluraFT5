package com.alura.forum.dto;

import com.alura.forum.entities.Curso;

import jakarta.validation.constraints.NotEmpty;

public class CursoDTO {

	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String categoria;
	
	public CursoDTO() {
	}

	public CursoDTO(Long id, @NotEmpty String nome, @NotEmpty String categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}

	public CursoDTO(Curso entity) {
		id = entity.getId();
		nome = entity.getNome();
		categoria = entity.getCategoria();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
