package com.alura.forum.dto;

import java.util.HashSet;
import java.util.Set;

import com.alura.forum.entities.Curso;
import com.alura.forum.entities.Usuario;

public class CursoDTO {

	private Long id;
	private String nome;
	private String categoria;
	
	private Set<UsuarioDTO> usuariosDTO = new HashSet<>();
	
	public CursoDTO() {
	}

	public CursoDTO(Long id, String nome, String categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}
	
	public CursoDTO(Curso entity) {
		id = entity.getId();
		nome = entity.getNome();
		categoria = entity.getCategoria();
	}

	public CursoDTO(Curso entity, Set<Usuario> usuarios) {
		this(entity);
		usuarios.forEach(u -> usuariosDTO.add(new UsuarioDTO(u)));
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

	public Set<UsuarioDTO> getUsuarios() {
		return usuariosDTO;
	}
}
