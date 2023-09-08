package com.alura.forum.dto;

public class UsuarioNovoDTO extends UsuarioDTO{

	private String senha;
	
	public UsuarioNovoDTO() {
	}

	public UsuarioNovoDTO(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
