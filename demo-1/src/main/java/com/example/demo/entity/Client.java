package com.example.demo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "client")
public class Client {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
	 @Column(name = "email")
	    private String email;

	    @Column(name = "phone_number")
	    private String phoneNumber;
	    @Column(name = "fullname")
	    private String fullname;

	    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
	    private Set<RequestOrder> requestOrders = new HashSet<>();

	    public void addRequestOrder(RequestOrder requestOrder){
	        requestOrders.add(requestOrder);
	        requestOrder.setClient(this);
	    }

}
