package com.alura.forum.configurations;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	@Order(1)
	SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher(PathRequest.toH2Console())
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers
					.frameOptions(frameOptions -> frameOptions
							.disable()));
		return	http.build();
	}
	
	@Bean
	@Order(2)
	SecurityFilterChain standardSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf
						.disable())
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().permitAll())
				.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
