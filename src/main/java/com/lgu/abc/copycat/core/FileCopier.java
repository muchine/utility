package com.lgu.abc.copycat.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileCopier {

	private final List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
	
	private boolean enabled = true;
	
	public FileCopier(String source, String destination) {
		addConfiguration(new CopycatConfiguration(source, destination));
	}
	
	public FileCopier(List<CopycatConfiguration> configurations) {
		this.configurations.addAll(configurations);
	}
	
	public void addConfiguration(CopycatConfiguration configuration) {
		configurations.add(configuration);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void copy(boolean enabled) {
		setEnabled(enabled);
		copy();
	}
	
	public void copy() {
		System.out.println("starting copy...");
		for (CopycatConfiguration configuration : configurations) {
			String source = configuration.source().getAbsolutePath();
			
			System.out.println("starting copy from source : " + source);			
			copy(configuration.source(), configuration.destination(), configuration);
		}
		System.out.println("copy done...");
	}
	
	private void copy(File source, File destination, CopycatConfiguration configuration) {
		if (!source.exists()) {
			System.out.println("### source does not exist!! " + source.getAbsolutePath());
			return;
		}
		
		for (File sourceChild : source.listFiles()) {
			File destinationChild = findInDestination(sourceChild, destination);
			
			if (configuration.isSkipped(sourceChild)) continue;
			
			if (sourceChild.isDirectory()) {
				copyDirectory(sourceChild, destinationChild, configuration);
			} else {
				copyFile(destination, sourceChild, destinationChild, configuration);	
			}
		}
	}
	
	private void copyDirectory(File sourceChild, File destinationChild, CopycatConfiguration configuration) {
		if (!configuration.isRecursive()) return;
		
		if (!destinationChild.exists()) destinationChild.mkdir();
		copy(sourceChild, destinationChild, configuration);
	}
	
	private void copyFile(File destination, File sourceChild, File destinationChild, CopycatConfiguration configuration) {
		try {
			String parentPath = configuration.source().getAbsolutePath();
			String relativePath = sourceChild.getAbsolutePath().replace(parentPath, "");
			
			if (!destinationChild.exists()) {
				System.out.println("... create file " + relativePath);
				if (enabled) FileUtils.copyFileToDirectory(sourceChild, destination);
			} else if ( isUpdated(sourceChild, destinationChild)) {
				System.out.println("... update file " + relativePath);
				if (enabled) FileUtils.copyFile(sourceChild, destinationChild); 
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private File findInDestination(File file, File destination) {
		return new File(destination.getAbsolutePath(), file.getName());
	}
	
	private boolean isUpdated(File sourceChild, File destinationChild) {
		return sourceChild.lastModified() > destinationChild.lastModified();
	}
		
	public static final void main(String[] args) {
		FileCopier copier = new FileCopier(
			"D:\\Workspace\\test\\abc.office\\abc.address\\src\\main\\java\\com\\lgu\\abc\\address", 
			"D:\\Workspace\\abc\\abc.office\\src\\main\\java\\com\\lgu\\abc\\address");
		copier.copy(true);
	}
	
}
