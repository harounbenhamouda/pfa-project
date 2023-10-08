package com.example.demo.dtao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.entity.Authorities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor 
public class LoginResponse {
	
	
	private String email;
	private String token;
	private List roles;

}