package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;


@Service
public class CountryService {
	@Autowired
 CountryRepository countryrepository;

	public List<Country> getallcoutries(){
		return  countryrepository.findAll();
	}
	
	
	
	
	
}
