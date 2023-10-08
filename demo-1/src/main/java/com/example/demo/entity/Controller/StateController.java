package com.example.demo.entity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.State;
import com.example.demo.service.StateService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class StateController {
	@Autowired
	StateService stateservice;
@GetMapping("/allstates")
public List<State> getallstates(){
	return stateservice.getallstates();
}
@GetMapping("/statebycode")
public List<State> getByCountryCode(@RequestParam String code){
	return stateservice.findByCountryCode(code);
}
}
