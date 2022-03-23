package com.springboot.SpringBootJenkinsIntegration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping(value="/welcome")
	public static String Welcome() {
	    return "Welcome to Spring Boot \n" +
		"Spring Boot Started Welcome Screen";
	}
	
	@RequestMapping(value="/")
	public static String HomeScreen() {
	    return "Welcome to Spring Boot Home Screen \n";
	}
	
	
}
