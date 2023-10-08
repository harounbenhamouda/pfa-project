 package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderFood {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	private String nameOrder;
	 
	private double  price;
	private String img;
	@Lob
	private String description;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category;
	@CreationTimestamp
	private Date ordCreate;
	@UpdateTimestamp
	private Date ordUpdate;
	
}
