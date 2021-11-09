package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.repository.LessonRepository;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/lesson")
public class LessonsControler {
	
	@Autowired
	LessonRepository lessonrepository;
	
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	

}
