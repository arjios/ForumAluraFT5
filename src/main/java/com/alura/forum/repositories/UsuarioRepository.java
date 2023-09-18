package com.alura.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.forum.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(nativeQuery = true, value = """
			SELECT * FROM tb_usuario WHERE tb_usuario.email = :email
			""")
	Usuario findByEmail(String email);

}
