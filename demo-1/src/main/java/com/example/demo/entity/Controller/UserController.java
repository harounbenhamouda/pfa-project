package com.example.demo.entity.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtao.AccountResponse;
import com.example.demo.dtao.ActiveAccount;
import com.example.demo.dtao.JwtLogin;
import com.example.demo.dtao.LoginResponse;
import com.example.demo.dtao.Mail;
import com.example.demo.dtao.NewPassword;
import com.example.demo.dtao.ResetPassword;
import com.example.demo.dtao.UserActive;
import com.example.demo.entity.Authorities;
import com.example.demo.entity.Code;
import com.example.demo.entity.User;
import com.example.demo.service.AuthoritiesService;
import com.example.demo.service.EMailService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.util.UserCode;

import javassist.NotFoundException;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class UserController {
	
	private TokenService tokenService;
    private UserService userService;
   private AuthoritiesService authoriesservice;
    private PasswordEncoder passwordEncoder;
    private EMailService emailservice;
    
    
    @PostMapping("/signin")
    public LoginResponse logIn(@RequestBody JwtLogin jwtLogin){
        return tokenService.login(jwtLogin);
    }

@Autowired
	
    public UserController(TokenService tokenService, UserService userService, AuthoritiesService authoriesservice,
			PasswordEncoder passwordEncoder, EMailService emailservice) {
		
		this.tokenService = tokenService;
		this.userService = userService;
		this.authoriesservice = authoriesservice;
		this.passwordEncoder = passwordEncoder;
		this.emailservice= emailservice;
	}


    @PostMapping("/signup")
	public AccountResponse createUser(@RequestBody JwtLogin jwtLogin) {
    	User user=new User();
    	AccountResponse accountres= new AccountResponse();
    	boolean result = userService.existbyemail(jwtLogin.getEmail());
    	if (result) { 
    		accountres.setResult(0);
    	}
    	else {
    		 String myCode = UserCode.getCode();
    user.setEmail(jwtLogin.getEmail());
    user.setPassword(passwordEncoder.encode(jwtLogin.getPassword()));
    user.setActive(0);
   user.getAuthorities().add(authoriesservice.getAuthorities().get(0));
    
    
   Mail mail = new Mail(jwtLogin.getEmail(),myCode);
    emailservice.sendCodeByMail(mail);
    Code code = new Code();
    code.setCode(myCode);
    user.setCode(code);
    userService.addUser(user);
    accountres.setResult(1);
    
    }
    	return accountres;
    	
    }
    @PostMapping("/active")
		public UserActive getActivateUser(@RequestBody JwtLogin jwtlogin) {
			String enPassword= userService.getPasswordByEmail(jwtlogin.getEmail());
			 boolean result = passwordEncoder.matches(jwtlogin.getPassword(),enPassword);
			  System.out.println(result);
			  UserActive useractive =new UserActive();
		if (result) {
			
			int act=userService.getUserActive(jwtlogin.getEmail());
			useractive.setActive(act);
		}
		else {
			useractive.setActive(-1);
			
		}
 return  useractive;
}
    @PostMapping("/activate")
    public AccountResponse activateAccount(@RequestBody ActiveAccount activeaccount) {
    	User user=userService.findByEmail(activeaccount.getEmail());
    	AccountResponse accountres= new AccountResponse();
    	UserActive useractive =new UserActive();
    	if (user.getCode().getCode().equals(activeaccount.getCode())) {
    		
    		 user.setActive(1);
    		 userService.updateUser(user);
    		 accountres.setResult(1);
    	}
    	else {
    		accountres.setResult(0);
    		
    	}
    	
    	return accountres;
    }
    @PostMapping("/reset")
    public  AccountResponse Resetpassowrd(@RequestBody ResetPassword resetpassword) {
    	//Boolean res=userService.existbyemail(resetpassword.getEmail());
    	User user =this.userService.findByEmail(resetpassword.getEmail());
    	AccountResponse accountresponse = new AccountResponse();
    	if(user !=null) {
    		 String myCode = UserCode.getCode();
    		Mail mail= new Mail(resetpassword.getEmail(),myCode);
    		emailservice.sendCodeByMail(mail);
    		user.getCode().setCode(myCode);
    		userService.updateUser(user);
    		accountresponse.setResult(1);
    	}
    	else {
    		accountresponse.setResult(0);
    	}
    	return accountresponse;
    }
    @PostMapping("/changepassword")
     public AccountResponse changePassword(@RequestBody NewPassword newpassword) {
    	 User user =this.userService.findByEmail(newpassword.getEmail());
     	AccountResponse accountresponse = new AccountResponse();
     	if(user!= null) {
     	if(user.getCode().getCode().equals( newpassword.getCode()) ){
     		user.setPassword(passwordEncoder.encode(newpassword.getNewpassword()));
     		userService.updateUser(user);
     		accountresponse.setResult(1);
     		
     	}else {
     		
     	}
     	
     	}
     	else {accountresponse.setResult(0);
     		
     	}
     	return accountresponse;
    	 
     }
    
    
    @GetMapping("/user/{id}")
    public ResponseEntity<Map <String ,Object>> getUserById(@PathVariable Long id) {
    	User user = null;
    	 Map <String ,Object>map = new HashMap();
    	 List<String> roles= new ArrayList<>();
        try {
			 user= userService.getUserById(id) ;
if (user != null ) {
	
	for(Authorities x:user.getAuthorities()) {
		roles.add(((GrantedAuthority) x).getAuthority());
	}
} 
map.put("user", user);
map.put("roles", roles);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return  new  ResponseEntity<>(user,e);
		}
		return  new  ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
    
    @GetMapping("user/connected")
    public Map <String ,Object> getCurrentUser(Authentication authentication){
    	 Map <String ,Object>map = new HashMap();
    	 map.put("user",authentication );
    	 map.put("roles", authentication.getAuthorities());
    	 
        return map;
    }
    
    
    
		}
