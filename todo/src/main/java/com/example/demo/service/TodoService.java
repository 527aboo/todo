package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.entity.User;
import com.example.demo.form.TodoForm;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> getTodosByUser(User user) {
		return todoRepository.findByUser(user);
	}
	
	public void createTodo(TodoForm form, User user) {
		
		Todo todo = new Todo();
		todo.setTitle(form.getTitle());
		todo.setDescription(form.getDescription());
		todo.setDueDate(form.getDueDate());
		todo.setUser(user);
		todoRepository.save(todo);
	}
	
	public Todo getTodoByIdAndUser(Long Id, User user) {
		return todoRepository.findById(Id)
				.filter(todo -> todo.getUser().getId().equals(user.getId()))
				.orElseThrow(() -> new IllegalArgumentException("対象のToDoが見つかりません。"));
	}
	
	public void updateTodo(TodoForm form, User user) {
		Todo todo = getTodoByIdAndUser(form.getId(), user);
		todo.setTitle(form.getTitle());
		todo.setDescription(form.getDescription());
		todo.setDueDate(form.getDueDate());
		todoRepository.save(todo);
	}
}
