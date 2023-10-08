package com.example.demo.entity.Controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
 
	 public static String uploadDirectory= "C:\\Users\\Haroun\\book-store\\src\\assets\\img";
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	registry
	        .addResourceHandler("/book-store/src/assets/img/**")
	        .addResourceLocations("file:"+uploadDirectory+"\\");
	    }
	}
