package com.lgu.abc.copycat.resource;

import java.util.ArrayList;
import java.util.List;

import com.lgu.abc.copycat.core.CopycatConfiguration;

public class CommonResource {

	public static final String OLD_ROOT = "D:\\Workspace\\abc\\abc.office";
	
	public static final String NEW_ROOT = "D:\\Workspace\\test\\abc.office";
		
	public static final String JAVA_PATH = "src\\main\\java\\com\\lgu\\abc";
	
	public static final String JAVA_RESOURCE_PATH = "src\\main\\resources";
	
	public static final String TEST_PATH = "src\\test\\java\\com\\lgu\\abc";
	
	public static final String TEST_RESOURCE_PATH = "src\\test\\resources";
	
	public static final String JS_PATH = "src\\main\\webapp\\resources\\abc\\js";
	
	public static final String CSS_PATH = "src\\main\\webapp\\resources\\abc\\css";
	
	public static final String JSP_PATH = "src\\main\\webapp\\WEB-INF\\com\\lgu\\abc";
	
	private final String source;
	
	private final String destination;
	
	private final String packageName;
	
	private final String[] jsNames;
	
	private final String[] jspNames;
	
	private final String[] cssNames;
	
	CommonResource(String source, String destination, String packageName, String[] jsNames, String[] jspNames, String[] cssNames) {
		this.source = source;
		this.destination = destination;
		this.packageName = packageName;
		this.jsNames = jsNames;
		this.jspNames = jspNames;
		this.cssNames = cssNames;
	}
	
	public CopycatConfiguration configuration(String path) {
		return new CopycatConfiguration(source + "\\" + path, destination + "\\" + path);
	}
	
	CopycatConfiguration javaConfiguration() {
		return configuration(JAVA_PATH + "\\" + packageName);
	}
	
	CopycatConfiguration javaResourceConfiguration() {
		return configuration(JAVA_RESOURCE_PATH);
	}
	
	CopycatConfiguration testConfiguration() {
		return configuration(TEST_PATH + "\\" + packageName);
	}
	
	CopycatConfiguration testResourceConfiguration() {
		return configuration(TEST_RESOURCE_PATH);
	}
	
	List<CopycatConfiguration> jsConfigurations() {
		return webConfigurations(jsNames, JS_PATH);
	}
	
	List<CopycatConfiguration> cssConfigurations() {
		return webConfigurations(cssNames, CSS_PATH);
	}
	
	List<CopycatConfiguration> jspConfigurations() {
		return webConfigurations(jspNames, JSP_PATH);
	}
	
	private List<CopycatConfiguration> webConfigurations(String[] names, String path) {
		List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
		
		for (String name : names) {
			if (name == null | name.isEmpty()) continue;
			configurations.add(configuration(path + "\\" + name));
		}
		
		return configurations;
	}
	
}
