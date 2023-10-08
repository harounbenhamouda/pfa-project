package com.example.demo.entity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.OrderFood;
import com.example.demo.service.OrderFoodService;

@CrossOrigin("*")
@RestController
@RequestMapping({"/api","/admin"})
public class OrderFoodController {
	@Autowired
	OrderFoodService ofs;
	@GetMapping("/allfoodorders")
	@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
public List<OrderFood>getAllOrderFood(@RequestParam int page,@RequestParam int size){
	return ofs.getAllOrderFood(page, size);
}  @GetMapping("/category")
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
	public List<OrderFood>getOrderByIdCategory( @RequestParam int page,@RequestParam int size ,@RequestParam Long id){
		return ofs.getOrderByIdCategory( page,size,id);
	}
@GetMapping("/foodbyname")
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
public List<OrderFood>getOrderByName(@RequestParam int page,@RequestParam int size,@RequestParam String  word){
	return ofs.getOrderByName(page,size,word);
}
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
@GetMapping("/foodbyid")
public OrderFood getOrderFoodById(@RequestParam Long id) {
	return ofs.getOrderFoodById(id);
}
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
@GetMapping("/ordersize")
public Long getordersize( ) {
	return ofs.getFullOrderSize();
}
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
@GetMapping("/ordersizebycategoryid")
public Long getorderlengthBycategoryid(@RequestParam Long id) {
	return ofs.getorderlengthBycategoryid(id);
}
@PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_USER')")
@GetMapping("/ordersizebykey")
public Long getordersizebykey(@RequestParam("word") String word) {
	return ofs.getordersizebykey(word);

}
@PreAuthorize("hasRole('ROLE_ADMIN')  ")
@DeleteMapping("/deletefood")

public void deleteFoodById(@RequestParam Long id) {
	ofs.deleteFoodById(id);
	
}
@PreAuthorize("hasRole('ROLE_ADMIN')  ")
@PostMapping("/addFood")
public void addFood(@RequestParam( name="food", required = false) String orderfood ,@RequestParam(name= "f", required = false) MultipartFile file)throws Exception {
ofs.addFood(orderfood, file);
}











}