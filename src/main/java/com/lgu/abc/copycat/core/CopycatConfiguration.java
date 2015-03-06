package com.lgu.abc.copycat.core;

import java.io.File;

public class CopycatConfiguration {

	private final File source;
	
	private final File destination;
	
	private final String[] skippingDirectory = new String[] {".svn"};
	
	private boolean recursive = true;
	
	public CopycatConfiguration (String source, String destination) {
		this.source = new File(source);
		this.destination = new File(destination);
	}
	
	public File source() {
		return source;
	}
	
	public File destination() {
		return destination;
	}
	
	public CopycatConfiguration recursive(boolean recursive) {
		this.recursive = recursive;
		return this;
	}
	
	public boolean isRecursive() {
		return recursive;
	}
	
	public boolean isSkipped(File file) {
		return isSkippedByName(file.getName());
	}
	
	private boolean isSkippedByName(String directoryName) {
		for (String name : skippingDirectory) {
			if (name.equals(directoryName)) return true;
		}
		
		return false;
	}
	
}
