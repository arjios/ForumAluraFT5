package com.alura.forum.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.alura.forum.dto.UsuarioDTO;
import com.alura.forum.entities.Usuario;
import com.alura.forum.repositories.UsuarioRepository;
import com.alura.forum.services.exceptions.ServiceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public List<UsuarioDTO> buscarTodosUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList()); 
	}
	
	@Transactional
	public Page<UsuarioDTO> buscarTodosUsuariosPaginados(Pageable pageable) {
		Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
		return usuarios.map(c -> new UsuarioDTO(c));	
	}
	
	@Transactional
	public UsuarioDTO buscarUsuarioPorId(Long id) {
		String msgErro = "Erro: Busca do Usuario com id " + id + " não encontrado.";
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioDTO inserirUsuario(UsuarioDTO dto) {
		String msgErroInserir = "Erro: Não foi posssivel inserir um novo Usuario.";
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
		String msgErro = "Erro: Não foi posssivel atualizar o Usuario.";
		Usuario usuario = new Usuario();
		usuario = usuarioRepository.findById(id)
					.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
		return new UsuarioDTO(usuario);
	}
	
	public void excluirUsuarioPorId(Long id) {
		String msgErro = "Erro: Não foi posssivel excluir o Usuario. Id: " + id + " não existe.";
		usuarioRepository.findById(id)
						.orElseThrow(() -> new ServiceNotFoundException(msgErro));
		usuarioRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username);
	}

}
