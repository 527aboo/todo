package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UserRegisterForm;
import com.example.demo.service.UserSerive;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserSerive userService;
	
	// 登録フォーム表示
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new UserRegisterForm());
		return "users/register";
	}
	
	// 登録処理
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userForm") UserRegisterForm form, Model model) {
		userService.register(form.getUsername(), form.getEmail(), form.getPassword());
		model.addAttribute("message", "ユーザー登録が完了しました");
		return "users/register_success";
	}

}
