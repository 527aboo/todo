package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Todo;
import com.example.demo.entity.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	List<Todo> findByUser(User user);
	
	List<Todo> findByUserOrderByIdAsc(User user);

}
