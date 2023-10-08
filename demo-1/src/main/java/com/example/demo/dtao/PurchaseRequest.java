package com.example.demo.dtao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.entity.Address;
import com.example.demo.entity.Client;
import com.example.demo.entity.Item;
import com.example.demo.entity.RequestOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor
public class PurchaseRequest {
	
	private Client client;
    private RequestOrder requestOrder;
    private List<Item> items;
    private Address fromAddress;
    private Address toAddress;
	

}
