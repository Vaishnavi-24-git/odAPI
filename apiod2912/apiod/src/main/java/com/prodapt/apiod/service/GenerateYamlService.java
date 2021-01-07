package com.prodapt.apiod.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.prodapt.apiod.model.UserClass;

@Service
public class GenerateYamlService {
	
	@Value("${application.base.swaggerTemplate}")
	private String swaggerTemplate;
	
	@Value("${application.base.yamlPath}")
	private String yamlPath;
	
	@Value("${application.base.jenkinsTemplate}")
	private String jenkinsTemplate;
	
	@Value("${application.base.projectpath}")
	private String projectpath;
	
	@Autowired
	GenerateJenkinsService generateJenkinsService;
	
	@Autowired
	CreateProjectService createProjectService;
	
	@Autowired
	CreateRepositoryService createRepositoryService;
	
	String finalMessage=null;
	
	/**
	 * Method to generate YAML file
	 *
	 * @return String
	 * @throws Exception
	 */
	public String generateYaml(String userName, String project) throws IOException {
		try {
			String finalStr = swaggerTemplate;
			UserClass userClass = new UserClass(project, userName, finalStr);
			userClass.setProject(project);
			userClass.setUserName(userName);
			userClass.setYamlPath(finalStr);
			finalMessage=this.CreateSetUp(userClass);
		} catch (Exception e) {
			return "Failure";
		}
		return finalMessage;
	}
    
	
	 /**
     * Method to Save YAML file
     *
     * @return String
     * @throws Exception
     */
	public String saveYaml(MultipartFile file, String userName, String project) throws Exception {
		try {
			File saveFile = new File(yamlPath + File.separator +userName+ File.separator +project);
			saveFile.mkdirs();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path path = Paths.get(saveFile+ File.separator+ fileName);
			System.out.println(path);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//			jenkinsService.jenkinsFileGeneration(jenkinsTemplate,projectpath,userName,project);
			
			UserClass userClass = new UserClass(project, userName, path.toString() );
			userClass.setProject(project);
			userClass.setUserName(userName);
			userClass.setYamlPath(path.toString());
			
			finalMessage=this.CreateSetUp(userClass);
			
		} catch (IOException e) {
			e.printStackTrace();
			return "Failure";
		}
		return finalMessage;

	}
	
	
	public String CreateSetUp(UserClass userClass) throws Exception {
		String Message = null;
	    try {
			Boolean isProjectCreated=createProjectService.createProject(userClass.getUserName(), userClass.getProject(),userClass.getYamlPath());

			if(isProjectCreated) 
			{	
			System.out.println("inside create setup");	
				Boolean isJenkinsFileCreated = generateJenkinsService.jenkinsFileGeneration(jenkinsTemplate, projectpath, userClass.getUserName(), userClass.getProject());

				if(isJenkinsFileCreated)
				{	
					Boolean isRepositoryCreated = createRepositoryService.createRepository(userClass.getUserName(), userClass.getProject());    
					if(isRepositoryCreated) {
						Boolean isProjectPushed = createProjectService.gitPull(userClass.getUserName(), userClass.getProject());
						if(isProjectPushed){

						Message="Project pushed";
						}
					
					else {
						Message="Failed to push project";	
					}
				}
				else{
					Message = "Failed to create Repository";
				}
				}
				else 
				{	
					Message="Failed to Create Jenkins File";	
				}
			
			}
			else 
			{	
				Message= "Failed to Create Project";	
			}
		

		}catch (Exception e) {
		}
		return Message;

	}

}
