package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.jdbc.Expectations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.OrderFood;
import com.example.demo.repository.OrderFoodRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderFoodService   {
@Autowired 
OrderFoodRepo ofr;
public List<OrderFood>getAllOrderFood(int page,int size){
	Pageable pageable=PageRequest.of(page,size);
	return ofr.findAll(pageable).getContent();
}
public List<OrderFood>getOrderByIdCategory(int page,int size,Long id){
	Pageable pageable=PageRequest.of(page, size);
	return ofr.findByCategoryId(id,pageable);
	
}
public List<OrderFood>getOrderByIdCategory(Long id){
	
	return ofr.findByCategoryId(id, null);
	
}



public List<OrderFood>getOrderByName(int page , int size,String word){
	Pageable pageable=PageRequest.of(page, size);
	return ofr.findByNameOrderContaining(word,pageable);
}
public OrderFood getOrderFoodById(Long idfood) {
	return ofr.findById(idfood).get();
}
public long getFullOrderSize() {
	return ofr.count();
}
public Long getorderlengthBycategoryid(Long id) {
	return ofr.getorderlengthBycategoryid(id);
	
}
public Long getordersizebykey(String word) {
	return ofr.getordersizebykey( word);

}
public void deleteFoodById(Long id) {
	 ofr.deleteById(id);
}
public byte[] getImage(Long id) throws IOException {
String photo=ofr.getById(id).getImg();
File f=new File(System.getProperty("user.home"));
Path p=Paths.get(f+"/book-store/src/assets/img/"+photo);
return Files.readAllBytes(p);
}

private String uploadImage(MultipartFile file )throws Exception
{
	String nomFile=file.getOriginalFilename();// ali.jpg->ali_1234656.jpg
	String tab[]=nomFile.split("\\.");//ali|jpg
	String nomModif=tab[0]+System.currentTimeMillis()+"."+tab[1];
	File f=new File(System.getProperty("user.home")+"/book-store/src/assets/img/"+nomModif);
	FileOutputStream fos=new FileOutputStream(f);
	fos.write(file.getBytes());
	return nomModif;
	
}
public void addFood(String  orderfood , MultipartFile file) throws Exception{
	
	OrderFood order= new ObjectMapper().readValue(orderfood,OrderFood.class);
	String nomImage=uploadImage(file);
	order.setImg(nomImage);
	ofr.save(order);
	
}








}
