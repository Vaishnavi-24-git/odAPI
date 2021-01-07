package com.prodapt.apiod.controller;

import java.io.IOException;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class CommonController {

	@GetMapping("/getMessage")
	public final String getMessage() throws IOException {
     String values="Hi..Welcome";
 		return values;
	}
	
	
}
