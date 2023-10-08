package com.example.demo.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.User;
import com.example.demo.repository.AuthoritiesRepository;
import com.example.demo.repository.UserRepository;

@Service

public class DbInit implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	private AuthoritiesRepository authoritiesRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public DbInit(UserRepository userRepository, AuthoritiesRepository authoritiesRepository,
			PasswordEncoder passwordEncoder) {
		
		this.userRepository = userRepository;
		this.authoritiesRepository = authoritiesRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
    

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*User user = new User();
		user.setEmail("harounbenhamouda8@gmail.com");
		user.setPassword(passwordEncoder.encode("123456789"));
		user.setActive(1);
		List<Authorities> authorities = authoritiesRepository.findAll();
		user.getAuthorities().add(authorities.get(0));
		userRepository.save(user);*/
		
		
	}

}
