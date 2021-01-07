package com.prodapt.apiod.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateRepositoryService {

	@Autowired
	private Environment env;


	public Boolean createRepository(@RequestParam String username, @RequestParam String project) throws Exception {
	//	username = "User";
	//	project = "apiod";
		RestTemplate restTemplate = new RestTemplate();
		String url = env.getProperty("gitea.url");
		String base64Creds = env.getProperty("gitea.token");
		ResponseEntity<String> response = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "token " + base64Creds);
			headers.add("Content-Type", "application/json");     
			Map<String,Object > bodyParamMap = new HashMap<String, Object>();
			bodyParamMap.put("auto_init", true);
			bodyParamMap.put("default_branch", "master");
			bodyParamMap.put("description", "test repo");
			bodyParamMap.put("name", username+"_"+project);
			bodyParamMap.put("private", true);
			bodyParamMap.put("template", true);
			bodyParamMap.put("trust_model", "default");

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(bodyParamMap, headers);

			response = restTemplate.postForEntity(url, entity, String.class);

			return true;
		}
		catch(Exception e) {
//			e.printStackTrace();
			response = new ResponseEntity<String>("Error occurred", HttpStatus.EXPECTATION_FAILED);
			return false;
		}

	}
}
