package com.prodapt.apiod.service;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectService {

	@Value("${application.base.projectpath}")
	private String projectpath;

	Boolean message = null;
	public Boolean createProject(String userName,String project,String yamlPath) throws Exception  {

		try {
			File createDirectory  = new File(projectpath + File.separator +userName+ File.separator +project);
			createDirectory.mkdirs();
//			ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c","null >newfile.txt");
			ProcessBuilder pb = new ProcessBuilder("bash","-c","java -jar /home/vaishnavi_s/pullTestYaml/apiod2912/apiod/target/classes/swagger-codegen-cli.jar generate  -i /home/vaishnavi_s/pullTestYaml/apiod2912/apiod/target/classes/swaggerTemp.yaml   --api-package com.prodapt.example.api   --model-package com.prodapt.example.model   --group-id com.prodapt.example  --artifact-id spring-"+userName+"-"+project+"   --artifact-version 0.0.1-SNAPSHOT   -l spring   -o spring-"+userName+"-"+project);

			pb=pb.directory(new File(projectpath + userName + File.separator + project +File.separator));
			pb.start();
			System.out.println("pb   "+pb+"createDirectory   "+createDirectory);
		//	return this.gitPull(userName, project);
			return true;
		} catch (IOException e) {
			System.out.println("Error in create project service");
		       	e.printStackTrace();
			return false;
		}
    //  return message;
		
	}

//	public Boolean  gitPull(String userName, String project, String yamlPath) throws Exception{
//		try{
//			File createDirectory1 = new File(projectpath + File.separator+ userName + File.separator + project + File.separator + "git");
//			createDirectory1.mkdirs();
//			ProcessBuilder pb = new ProcessBuilder("bash","-c","cd /home/vaishnavi_s/apiod/ProjectDirectory/vaishnavi/api/git","-c","git init","-c","git pull 'http://146.148.62.118:3000/testUser1/vaishnavi_api.git'","-c","cp -R /home/vaishnavi_s/apiod/ProjectDirectory/vaishnavi/api/spring-swagger-codegen-employee /home/vaishnavi_s/apiod/ProjectDirectory/vaishnavi/api/git", "-c","git add .","-c","git commit -m 'message'","-c","git remote add origin 'http://146.148.62.118:3000/testUser1/vaishnavi_api.git'","-c","git push -u origin master" );

//			pb = pb.directory(new File(projectpath+ userName + File.separator + project + File.separator+"git"));
//					pb.start();
//					return true;
//}
//catch(IOException e){
//	e.printStackTrace();
//	return false;
//}
//	}


      
	public Boolean gitPull(String userName,String project) throws Exception  {
	       
 	    System.out.println("username---"+userName+"   project----"+project);

            String[] commands = {"git init","git pull http://146.148.62.118:3000/testUser1/"+userName+"_"+project+".git","cp -R /home/vaishnavi_s/apiod/ProjectDirectory/"+userName+"/"+project+"/spring-"+userName+"-"+project+"  /home/vaishnavi_s/apiod/ProjectDirectory/"+userName+"/"+project+"/git","cp /home/vaishnavi_s/apiod/ProjectDirectory/"+userName+"/"+project+"/Jenkinsfile  /home/vaishnavi_s/apiod/ProjectDirectory/"+userName+"/"+project+"/git","git add .","git commit -m 'message'","git remote add origin http://146.148.62.118:3000/testUser1/"+userName+"_"+project+".git","git push -u origin master"};


            for(String command : commands) {
                execute(command,userName,project);
            }
	    return true;
        }


        public  void execute(String command,String userName, String project) throws InterruptedException, IOException {
		File createDirectory  = new File(projectpath + File.separator +userName+ File.separator +project+ File.separator +"git");
			createDirectory.mkdirs();
            ProcessBuilder builder = new ProcessBuilder("bash","-c",command);
	    builder.directory(new File(projectpath +userName+ File.separator +project+ File.separator +"git"));
            Process process = builder.inheritIO().start();
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String readline;
            while ((readline = reader.readLine()) != null) {
                System.out.println(readline);
            }
        }
		
}
