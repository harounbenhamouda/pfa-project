package com.example.demo.dtao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;

public class UserPrincipal implements UserDetails {
 @Autowired
	private User user;
 
 
 
	public UserPrincipal(User user) {
	
		
	this.user = user;
}

	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities= new ArrayList<>();
		user.getAuthorities().forEach(temp->{
			GrantedAuthority grantedauthority= new SimpleGrantedAuthority(temp.getRolename());
			authorities.add(grantedauthority);
		});
		return authorities;
	}

	public List getRoles() {
		List<String> roles= new ArrayList<>();
		for(GrantedAuthority x:getAuthorities()) {
			roles.add(x.getAuthority());
		}
		return roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.getActive() ==1;
	}


	
	

}
