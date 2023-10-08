package com.example.demo.entity;

import java.util.Date;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@CrossOrigin("*")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Country {
	
	
	
	@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private String nameCountry;
private String code;
@JsonIgnore
@OneToMany (mappedBy = "country")
private List<State> states;
}
