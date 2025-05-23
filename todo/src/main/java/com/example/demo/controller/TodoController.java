package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.Todo;
import com.example.demo.entity.User;
import com.example.demo.form.TodoForm;
import com.example.demo.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todos")
	public String listTodos (Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		// @AuthenticationPrincipalを使うことで、ログイン中の CustomUserDetailsが取得できる
		User user = userDetails.getUser();
		List<Todo> todos = todoService.getTodosByUser(user);
		model.addAttribute("todos", todos);
		return "/todos/todos";
	}
	
	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("todoForm", new TodoForm());
		return "/todos/todo_form";
	}
	
	@PostMapping("/todos")
	public String CreateTodo(@ModelAttribute("todoForm") TodoForm form,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		
		todoService.createTodo(form, userDetails.getUser());
		return "redirect:/todos/todos";
	}
	

}
