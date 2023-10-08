package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.State;
import com.example.demo.repository.StateRepository;

@Service
public class StateService {
	@Autowired
private StateRepository staterepository;


public List<State> getallstates(){
	return staterepository.findAll();
}
public List<State>  findByCountryCode(String code){
	return staterepository.findByCountryCode(code);
}
}
