package com.prodapt.apiod.model;


public class UserClass {

	private String userName;
	private String project;
	private String yamlPath;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getYamlPath() {
		return yamlPath;
	}
	public void setYamlPath(String yamlPath) {
		this.yamlPath = yamlPath;
	}
	public UserClass(String userName, String project, String yamlPath) {
		super();
		this.userName = userName;
		this.project = project;
		this.yamlPath = yamlPath;
	}
	
	@Override
	public String toString() {
		return "UserClass [userName=" + userName + ", project=" + project + ", yamlPath=" + yamlPath + "]";
	}
	
	
	

  
}
