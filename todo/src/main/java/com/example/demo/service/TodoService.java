package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
		return todoRepository.findByUserOrderByIdAsc(user);
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
		todo.setUpdatedAt(LocalDateTime.now());
		todoRepository.save(todo);
	}
	
	public boolean markAsCompleted(Long id, User user) {
		Optional<Todo> optionalTodo = todoRepository.findById(id);
		if (optionalTodo.isPresent()) {
			Todo todo = optionalTodo.get();
			if (!todo.getUser().getId().equals(user.getId())) {
				return false;
			}
			todo.setCompleted(true);
			todo.setUpdatedAt(LocalDateTime.now());
			todoRepository.save(todo);
			return true;
		}
		return false;
	}
}
