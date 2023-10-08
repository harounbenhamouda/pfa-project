package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Authorities;
import com.example.demo.repository.AuthoritiesRepository;
@Service
public class AuthoritiesService {
private AuthoritiesRepository authoriesrepository;
@Autowired
public AuthoritiesService(AuthoritiesRepository authoriesrepository) {
	super();
	this.authoriesrepository = authoriesrepository;
}

@Transactional(readOnly = true)
public List<Authorities> getAuthorities(){
    return authoriesrepository.findAll();
}
}
