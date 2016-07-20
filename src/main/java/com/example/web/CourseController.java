package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	
	@RequestMapping("/")
	public String rootPath(){
		return "redirect:/courses";
	}
	
	@RequestMapping("courses")
	public String coursesPath(){
		return "courses";
	}

}
