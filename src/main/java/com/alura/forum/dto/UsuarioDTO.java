package com.alura.forum.dto;

import com.alura.forum.entities.Usuario;

import jakarta.validation.constraints.NotEmpty;


public class UsuarioDTO {

	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String email;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, @NotEmpty String nome, @NotEmpty String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
