package com.alura.forum.dto;

import com.alura.forum.entities.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getUsername();
		senha = entity.getPassword();
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
