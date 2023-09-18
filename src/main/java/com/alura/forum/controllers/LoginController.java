package com.alura.forum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forum.dto.LoginDTO;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager manager;
	
	public ResponseEntity inputLogin(LoginDTO loginDTO) {		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getSenha());
		Authentication authentication = manager.authenticate(token);		
		return ResponseEntity.ok().build();
	}

}
