package com.example.demo1.runner;
import java.text.DecimalFormat;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Restcontrollerex {
	RestTemplate restTemplate = new RestTemplate();
	Map<Object, Object> countryList = restTemplate.getForObject("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json",Map.class);

	private static DecimalFormat df = new DecimalFormat("0.00");
	@GetMapping("/")
	public ResponseEntity<Object> Hello() {
		try {
			return ResponseHandler.generateResponse("hello from home page",HttpStatus.OK,null);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
		}
	}
	
	@PostMapping(path="/convertor",
			     consumes = "application/json",
	             produces = "application/json")
	public ResponseEntity<Object> currencyConvertor(@RequestBody Map<Object, Object> payload) {
		try {
			String from = payload.get("from").toString();
			String to = payload.get("to").toString();
			double val = (Double)payload.get("value");
			String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + from+"/"+ to + ".json";
			
			//validation
			if(!countryList.containsKey(to)) {
				throw new Exception("Currency code "+to + " is invalid.");
			}
			if(!countryList.containsKey(from)) {
				throw new Exception("Currency code "+from + " is invalid.");
			}
			if(val < 0) {
				throw new Exception("value should not be negative");
			}
			
			//api call for currency comparison
			Map<Object, Object> resData = restTemplate.getForObject(url,Map.class);
			double amount = (Double)resData.get(to)*val;
			return ResponseHandler.generateResponse("conversion is successfull",HttpStatus.OK,df.format(amount));
			
		} catch(Exception e) {
			throw new RouteNotFoundException(e.getMessage());
		}
	}
	
	@GetMapping("/er")
	public String customerrFunction() {
		throw new RouteNotFoundException("route not found");
	}
}
