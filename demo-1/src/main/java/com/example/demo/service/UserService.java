package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtao.UserPrincipal;
import com.example.demo.entity.Authorities;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userrepository;
    public List roles;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		User user=userrepository.findByEmail(email);
		System.out.println(user.getEmail()+"   "+user.getPassword());
		UserPrincipal userprincipal= new UserPrincipal(user);
		 roles =userprincipal.getRoles();
		 System.out.println(roles);
		return userprincipal;
	}

    @Transactional
    public void addUser(User user){
        userrepository.save(user);
    }

    @Transactional
    public Boolean  existbyemail(String email) {
    	return userrepository.existsByEmail(email);
    }
    
    @Transactional
    public int getUserActive(String email){
        return userrepository.getActive(email);
    }
    public String getPasswordByEmail(String email) {
    	return userrepository.getPasswordByEmail(email);
    }
    public User findByEmail(String email) {
    	return this.userrepository.findByEmail(email);
    }
    public void updateUser(User user) {
    	 this.userrepository.save(user);
    }
    
    public User getUserById(Long id) throws NotFoundException {
      User  user=  userrepository.findById(id).get();
      
		
      if (user== null) {
    	  throw new NotFoundException("user not found");
      }
      return user;
    }
   
    
}
