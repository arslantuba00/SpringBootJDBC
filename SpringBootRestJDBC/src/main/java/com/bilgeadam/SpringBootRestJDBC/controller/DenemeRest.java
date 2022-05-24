package com.bilgeadam.SpringBootRestJDBC.controller;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "deneme")
public class DenemeRest 
{
	@Value("${level.high}")
	public String level;
	
	@NonNull
	private ApplicationContext applicationContext;
	
	@GetMapping(path = "hello")
	public ResponseEntity<String> hello() 
	{
		return ResponseEntity.ok("Hello spring boot rest service with jdbc" + level);
	}
	
	@GetMapping(path="beans")
	public ResponseEntity<String> beans()
	{
		// localhost:8080/deneme/beans
		String[] names = applicationContext.getBeanDefinitionNames();
		StringBuilder builder = new StringBuilder(); 
		builder.append("-----" + names.length + "-----").append("<br>");
		Arrays.sort(names);
		for (String name : names) 
		{
		   builder.append(name + " - " + applicationContext.getBean(name).getClass().getName()).append("<br>");	
		}
		
		return ResponseEntity.ok(builder.toString());
	}
}
