package com.alura.forum.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UsuarioNovoDTO extends UsuarioDTO{

	@NotEmpty
	@Size(min = 6, max = 32)
	private String senha;
	
	public UsuarioNovoDTO() {
		super();
	}

	public UsuarioNovoDTO(String senha) {
		super();
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
