package com.lgu.abc.copycat.resource;

import java.util.ArrayList;
import java.util.List;

import com.lgu.abc.copycat.core.CopycatConfiguration;

public class CommitResource {
	
	private final CommonResource resource;
	
	public CommitResource(String projectName, String packageName) {
		this(projectName, packageName, packageName);
	}
	
	public CommitResource(String projectName, String packageName, String jsName) {
		this(projectName, packageName, jsName, packageName, jsName);
	}
	
	public CommitResource(String projectName, String packageName, String jsName, String cssName) {
		this(projectName, packageName, jsName, packageName, cssName);
	}
	
	public CommitResource(String projectName, String packageName, String jsName, String jspName, String cssName) {
		this(projectName, packageName, new String[] {jsName}, new String[] {jspName}, new String[] {cssName});
	}
	
	public CommitResource(String projectName, String packageName, String[] jsNames, String[] jspNames, String[] cssNames) {
		String source = CommonResource.NEW_ROOT+ "\\" + projectName;
		String destination = CommonResource.OLD_ROOT;
		
		this.resource = new CommonResource(source, destination, packageName, jsNames, jspNames, cssNames);
	}
	
	public List<CopycatConfiguration> configurations() {
		List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
		
		configurations.addAll(javaConfigurations());		
		configurations.addAll(webConfigurations());
		
		return configurations;
	}
	
	public CopycatConfiguration configuration(String path) {
		return resource.configuration(path);
	}
	
	public List<CopycatConfiguration> javaConfigurations() {
		List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
		
		configurations.add(resource.javaConfiguration());
		configurations.add(resource.javaResourceConfiguration());
		configurations.add(resource.testConfiguration());
		configurations.add(resource.testResourceConfiguration());
		
		return configurations;
	}
	
	public List<CopycatConfiguration> webConfigurations() {
		List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
		
		configurations.addAll(resource.jsConfigurations());
		configurations.addAll(resource.cssConfigurations());
		configurations.addAll(resource.jspConfigurations());
		
		return configurations;
	}
		
}
