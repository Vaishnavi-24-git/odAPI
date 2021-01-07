package com.prodapt.apiod.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

@Service
public class GenerateJenkinsService {
//@Value("classpath:Jenkinsfile")
// Resource resourceFile;
	
	public Boolean jenkinsFileGeneration(String jenkinsTemplate,String projectpath,String userName,String project){
		try{
			Path jenkinsFile =Paths.get(jenkinsTemplate);
		//	System.out.println("resource file  "+resourceFile);
	         //	ClassPathResource jenkinsFile = new ClassPathResource("Jenkinsfile");
			Path Project = Paths.get(projectpath+File.separator +userName+ File.separator +project+ File.separator+ "Jenkinsfile");
                        System.out.println("path of jenkins file  "+jenkinsFile+"  , "+Project);
			

			Files.copy(jenkinsFile, Project, StandardCopyOption.REPLACE_EXISTING);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		

	}

}
