package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoApiController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping("/{id}/complete")
	public ResponseEntity<?> completeTodo(@PathVariable Long id,
			@AuthenticationPrincipal CustomUserDetails customerDetails) {
		
		boolean updated = todoService.markAsCompleted(id, customerDetails.getUser());
		return updated ? ResponseEntity.ok().build() : ResponseEntity.status(403).build();
	}
	
	
}
