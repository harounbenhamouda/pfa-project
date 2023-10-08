package com.example.demo.entity.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CountryController {
@Autowired 
private CountryService countryservice;
@GetMapping("/allcountries")
public List<Country> getallcountries() {
	return countryservice.getallcoutries();
}


}
