package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.User;
@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
public User findByEmail(String email);
public Boolean existsByEmail(String email);
@Query("select u.active from User  u where u.email=?1 ")
public int getActive(String email);

@Query("select u.password from User u where u.email=?1")
public String getPasswordByEmail(String email);

}
