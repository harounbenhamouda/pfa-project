package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Code {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
	private String code;
	@OneToOne(mappedBy = "code")
	private User user;

}
