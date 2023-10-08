package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtao.PurchaseRequest;
import com.example.demo.dtao.PurchaseResponse;
import com.example.demo.entity.Client;
import com.example.demo.entity.Item;
import com.example.demo.entity.RequestOrder;
import com.example.demo.repository.ClientRepository;
import com.example.demo.util.UserCode;
@Service
public class ImpPurchaseService implements PurchaseService {
@Autowired
	private ClientRepository clientrepository;
  private UserCode code;
	@Transactional
	public PurchaseResponse addRequestOrder(PurchaseRequest  purchase) {
		// TODO Auto-generated method stub
		RequestOrder requestOrder= purchase.getRequestOrder();
        String mycode= code.getCode();
        System.out.println(mycode);
	    requestOrder.setCode(mycode);
	   /*  requestorder.setItems(purchase.getItemordered());
	     purchase.getItemordered().forEach(item -> item.setRequestorder(requestorder));*/
	    List<Item> items=purchase.getItems();
	    items.forEach(item ->requestOrder.addItem(item));
	    
	    
	     requestOrder.setFromAddress(purchase.getFromAddress());
	     requestOrder.setToAddress(purchase.getToAddress());
	    /*Set <RequestOrder> requestOrders= new HashSet<>();
	     requestOrders.add(requestorder);
	     purchase.getClient().setRequests(requestOrders);
	     requestorder.setClient(purchase.getClient());*/
	    purchase.getClient().addRequestOrder(requestOrder);
	
	clientrepository.save(purchase.getClient());
	return new PurchaseResponse(mycode,purchase.getClient().getFullname());	}
	
}
