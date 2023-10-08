package com.example.demo.service;

import com.example.demo.dtao.PurchaseRequest;
import com.example.demo.dtao.PurchaseResponse;

public interface PurchaseService {
	public PurchaseResponse addRequestOrder(PurchaseRequest  purchase);

}
