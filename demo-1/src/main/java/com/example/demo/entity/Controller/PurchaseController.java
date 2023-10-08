package com.example.demo.entity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtao.PurchaseRequest;
import com.example.demo.dtao.PurchaseResponse;
import com.example.demo.service.ImpPurchaseService;
import com.example.demo.service.PurchaseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PurchaseController {
	@Autowired
	PurchaseService ps;
	public PurchaseController(PurchaseService ps) {
		this.ps=ps;	}
	
	@PostMapping("/purchase")
	public PurchaseResponse addRequestOrder(@RequestBody PurchaseRequest  purchase) {
		return  ps.addRequestOrder(purchase);		
		
		
	}

}
