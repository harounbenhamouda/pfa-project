package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "user")
public class User {
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String email;
private String password;
private int active;
@ManyToMany(fetch=FetchType.EAGER)
@JoinTable(name="user_role",
joinColumns= {@JoinColumn(name="user_id")},
inverseJoinColumns= {@JoinColumn(name="authories_id")}

)
private List<Authorities> authorities= new ArrayList<>();
@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinColumn(name = "code_id")
private Code code;

public Object[] getRoles() {
	
	return authorities.stream().toArray();
}
}
