package com.example.demo1.runner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontrollerex {
	@GetMapping("/")
	public String Hello() {
		return "hello world";
	}
	@GetMapping("/view")
	public String helloFromView() {
		return "hello world from view endpoint";
	}
	@GetMapping("/er")
	public String customerrFunction() {
		throw new RouteNotFoundException();
	}
}
