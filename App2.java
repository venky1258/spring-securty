package com.mypack.Th6;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noAuth")
public class App2 {

	@GetMapping("/sayHi")
	public String sayHi() {
		return "hi";
	}

}
