package com.jobJunior.cursomc.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobJunior.cursomc.dto.EmailDTO;
import com.jobJunior.cursomc.security.JWTUtil;
import com.jobJunior.cursomc.security.UserSpringSecurity;
import com.jobJunior.cursomc.service.AuthService;
import com.jobJunior.cursomc.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
		authService.sendNewPassword(emailDTO.getEmail());
		return ResponseEntity.noContent().build();

	}
	
}
