package com.example.demo1.runner;

public class RouteNotFoundException extends RuntimeException {
	public RouteNotFoundException(String message) {
		super(message);
	}
}
