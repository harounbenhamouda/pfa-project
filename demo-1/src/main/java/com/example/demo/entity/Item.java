package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Item {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	private String img;
	private int  quantity;
private int price;
@JsonIgnore
@ManyToOne()
private RequestOrder requestOrder;
}
