package com.alura.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.forum.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
