package com.prodapt.apiod.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prodapt.apiod.service.CreateRepositoryService;


@CrossOrigin(origins = "*")
@RestController
public class RepositoryController {

    @Autowired
    CreateRepositoryService createRepositoryService;
    
	
//	@GetMapping("/createRepo")
//	public ResponseEntity<String> createRepository(@RequestParam String username, @RequestParam String project) throws IOException {
//		ResponseEntity<String> data = null;
//		try {
//	      data = createRepositoryService.createRepository(username, project);
//         }
//         catch(Exception e) {
//        	 e.printStackTrace();
// 			 data = new ResponseEntity<String>("Error occurred", HttpStatus.EXPECTATION_FAILED);
//
//         }
//		
//      return data;
//	}
//	

	
}
