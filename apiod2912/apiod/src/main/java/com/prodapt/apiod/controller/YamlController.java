package com.prodapt.apiod.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prodapt.apiod.service.GenerateYamlService;


@CrossOrigin(origins = "*")
@RestController
public class YamlController {
	@Autowired
	GenerateYamlService generateYamlService;
	String values;
	
	
	
	ResponseEntity<String> responseEntity = null;
	String response=null;
	
	
	@PostMapping("/generateYaml")
	public ResponseEntity<String> GenerateYaml(@RequestParam String userName,@RequestParam String project) throws IOException {
		
		try {
			response = generateYamlService.generateYaml(userName,project);
			responseEntity = new ResponseEntity<String>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<String>("Error occurred", HttpStatus.EXPECTATION_FAILED);		
		}
		return responseEntity;
		
	}
	
	
	@PostMapping("/SaveYamlFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam String userName,@RequestParam String project) throws IOException{
		try {
			response=generateYamlService.saveYaml(file, userName, project);
			responseEntity=new ResponseEntity<String>(response,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity= new ResponseEntity<String>("Error", HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	} 
	
	
	
}
