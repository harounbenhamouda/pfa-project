package com.example.demo.entity;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category  {
	
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	private String nameCategory;
	@JsonIgnore
	@OneToMany (mappedBy = "category",cascade = CascadeType.ALL)

	private List<OrderFood> orders;
	@CreationTimestamp
	private Date categCreate;
	@UpdateTimestamp
	private Date categUpdate;
	private String logoscategory;
	 
	 

	  

}
