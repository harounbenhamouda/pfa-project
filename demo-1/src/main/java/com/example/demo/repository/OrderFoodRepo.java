package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.OrderFood;

public interface OrderFoodRepo extends JpaRepository<OrderFood,Long> {
	public List<OrderFood> findByCategoryId(Long id,Pageable pageable) ;
	public List<OrderFood> findByNameOrderContaining(String nameOrder,Pageable pageable);
	@Query("select count(id) from OrderFood where category.id=?1")
	public Long getorderlengthBycategoryid(Long id);
	@Query("select count(id) from OrderFood where name_order LIKE '%1%'")
	public Long getordersizebykey(String word);
	public List<OrderFood> findByCategory(Long id) ;
	
}
