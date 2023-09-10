package com.alura.forum.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.forum.dto.UsuarioDTO;
import com.alura.forum.dto.UsuarioNovoDTO;
import com.alura.forum.entities.Usuario;
import com.alura.forum.repositories.UsuarioRepository;
import com.alura.forum.services.exceptions.ServiceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public List<UsuarioDTO> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	
	@Transactional
	public Page<UsuarioDTO> listarUsuarioPaginados(Pageable pageable) {
		Page<Usuario> pages = usuarioRepository.findAll(pageable);
		return pages.map(p -> new UsuarioDTO(p));
	}
	
	@Transactional
	public UsuarioDTO buscarUsuarioPorId(Long id) {
		String msgErro = "Erro: Busca do Usuario com id " + id + " não encontrado.";
		Usuario usuario = new Usuario();
		usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioNovoDTO inserirUsuario(UsuarioNovoDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario = usuarioRepository.save(usuario);
		return dto;
	}
	
	@Transactional
	public UsuarioDTO atualizarUsuario(Long id, UsuarioNovoDTO dto) {
		String msgErro = "Erro: Não existe Usuario com id " + id + " para atualizar.";
		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		usuario.setSenha(dto.getSenha());
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public void excluirUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
}
