package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;

@Controller
public class HomeController {
	//private CustomUserDetailsService customUserDetailsService;
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String hello(Model model) {
		return "index";
	}
	@GetMapping("/back")
	public String back() {
		return"back";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/registersucessful")
	public String rs(Model model) {
		return "registersucessful";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("user")  User user, BindingResult bindingResult, Model model,RedirectAttributes ra) {

		new UserValidator().validate(user, bindingResult);
		
		if(userService.findByUserName(user.getUsername())!=null) {
			bindingResult.rejectValue("username","user.username.duplicated","帳號已存在");
		}
		if (bindingResult.hasErrors()) {
			List<FieldError> list = bindingResult.getFieldErrors();
			return "register";
		}
		userService.save(user);	
		//需開啟google2階段認證 等到大家都做好在開啟
		//emailService.sendRegistrationConfirmation(user.getEmail(), user.getUsername());
	    return "redirect:/registersucessful";
	}

}