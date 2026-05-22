package com.example.demo.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.service.UserApplicationService;
import com.example.demo.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping ("/user" )
@Slf4j
public class SignupController {
	
	@Autowired
	private UserApplicationService userApplicationService ;

	/** Display the user signUp screen */
	@GetMapping ("/signup" )
	public String getSignup(Model model , Locale locale, @ModelAttribute SignupForm form)  {
		// Get gender
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model .addAttribute("genderMap" , genderMap );
		// Transition to user signUp screen
		return "hello/user/signup";
	}
	/** User signUp process */
	@PostMapping ("/signup" )
	public String postSignup(Model model , Locale locale ,@ModelAttribute  SignupForm form, BindingResult bindingResult) {
		
		// Input check result
		if (bindingResult .hasErrors()) {
			// NG: Return to the user signUp screen
			return getSignup(model,locale,form );
		}
		log .info(form .toString());
		
		// Redirect to login screen
		return "redirect:/login" ;
	}
	
	
}