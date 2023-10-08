package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.config.jwt.JwtAuthorizationFilter;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)

public class SecurityConfiguration  extends  WebSecurityConfigurerAdapter{
	@Autowired 
	private UserService userservice;
@Autowired
	private UserRepository userRepository;
	
	
	
	
	
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	protected void configure(HttpSecurity http) throws Exception {
        http   
              .csrf().disable()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
                 .authorizeRequests()
                // .anyRequest().permitAll()
                 .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
              // 
                 
                .antMatchers("/admin/**").authenticated()
               
              .antMatchers("/signin").permitAll()
              .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/active").permitAll()
                .antMatchers("/activate").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/reset").permitAll()
                .antMatchers("/changepassword").permitAll()
                .anyRequest().authenticated()               
                .and()
                
                .httpBasic();
                /*.formLogin()
                .loginProcessingUrl("/signadmin")
                .loginPage("/loginadmin").permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/home", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/loginadmin");*/
                
                
}
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("static/**");
	}
	
	
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userservice);
        return daoAuthenticationProvider;
    }
	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}