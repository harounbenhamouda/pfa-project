package com.example.demo.service;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.example.demo.dtao.JwtLogin;
import com.example.demo.dtao.JwtProperties;
import com.example.demo.dtao.LoginResponse;
import com.example.demo.dtao.UserPrincipal;
import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service

public class TokenService   {
	 private AuthenticationManager authenticationManager;
	// @Autowired
	 private  UserService userService=new UserService();
	
	    @Autowired
	    public TokenService(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	        
	    }
	    private String generateToken(Authentication authResult) {

	        // Grab principal
	        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
	        System.out.println(principal.getUsername());

	        // Create JWT Token
	        String token = JWT.create()
	                .withSubject(principal.getUsername())
	                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
	                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
	        return token;
	    }

	    public LoginResponse login(JwtLogin jwtLogin ) {
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtLogin.getEmail(),
	                jwtLogin.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authenticate);
	        SecurityContextHolder.getContext().getAuthentication();
	     System.out.println(jwtLogin.getEmail());	
	     // UserPrincipal userprincipal=   (UserPrincipal) userService.loadUserByUsername(jwtLogin.getEmail());
	        String token = generateToken(authenticate);
	        
	       List roles= grabPrincipal(authenticate);
	      // System.out.println(roles);
	        // List roles = new ArrayList();
	         //roles.add("Role_ADMIN");
	        
	        
	         
	      
	         
	         
	         
	        return new  LoginResponse(jwtLogin.getEmail(),token,roles);
	       

	
	    }
	    
	    
	    public  List grabPrincipal(Authentication authResult) {
	    	
	    	
	    	 UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
	    	 return principal.getRoles();
	    	 
	    }
	    
	    
	    
	    
	   
	

}
