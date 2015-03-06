package com.lgu.abc.copycat;

import java.util.ArrayList;
import java.util.List;

import com.lgu.abc.copycat.core.CopycatConfiguration;
import com.lgu.abc.copycat.core.FileCopier;
import com.lgu.abc.copycat.resource.CommitResource;
import com.lgu.abc.copycat.resource.CommonResource;

public class FileCommiter {
	
	public void synchronize() {
		FileCopier copier = new FileCopier(newConfigurations());
		copier.copy(false);
	}

	private List<CopycatConfiguration> newConfigurations() {
		List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
		
		configurations.addAll(core());  
		configurations.addAll(address());
		configurations.addAll(new CommitResource("abc.attendance", "attendance", "attendance", "attendance", "wrk").configurations());
		configurations.addAll(new CommitResource("abc.auth", "auth").configurations());
		configurations.addAll(new CommitResource("abc.board", "brd").configurations());
		configurations.addAll(new CommitResource("abc.calendar", "calendar", "sch", "calendar", "sch").configurations());
		configurations.addAll(new CommitResource("abc.calendar", "sch").configurations());
		configurations.addAll(new CommitResource("abc.common", "approval", "apr", "apr").configurations());
		configurations.addAll(new CommitResource("abc.common", "apr", "apr", "apr").configurations());
		configurations.addAll(new CommitResource("abc.equipment", "equipment", "equipment", "equipment", "eqm").configurations());
		configurations.addAll(new CommitResource("abc.home", "home").configurations());
		configurations.addAll(new CommitResource("abc.home", "om").configurations());
		configurations.addAll(new CommitResource("abc.journal", "jnl").configurations());
		configurations.addAll(new CommitResource("abc.mail", "mai").configurations());
		configurations.addAll(new CommitResource("abc.mail", "mail", "mail", "mai").configurations());
		configurations.addAll(new CommitResource("abc.messaging.note", "messaging\\note", "messaging\\note", "not").configurations());
		configurations.addAll(new CommitResource("abc.messaging.text", "messaging\\text", "messaging").configurations());
		configurations.addAll(org());
		configurations.addAll(new CommitResource("abc.photo", "photo", "photo", "photo", "pht").configurations());
		configurations.addAll(new CommitResource("abc.survey", "sve").configurations());
		configurations.addAll(new CommitResource("abc.webdisk", "doc").configurations());
		configurations.addAll(new CommitResource("abc.workspace", "workspace", "workspace", "ws").configurations());
		
		return configurations;
	}
	
	private List<CopycatConfiguration> core() {
		CommitResource core = new CommitResource("abc.core", "core");
		List<CopycatConfiguration> configurations = core.javaConfigurations();
		
		configurations.add(core.configuration(CommonResource.JS_PATH));
		configurations.add(core.configuration(CommonResource.CSS_PATH));
		configurations.add(core.configuration(CommonResource.JSP_PATH));
		
		return configurations;
	}
	
	private List<CopycatConfiguration> address() {
		CommitResource address = new CommitResource("abc.address", "address",
				new String[] {"address"}, new String[] {"address"}, new String[] {"address", "adr"});
		List<CopycatConfiguration> configurations = address.configurations();
		
		configurations.add(address.configuration("src\\main\\webapp\\files"));
		
		return configurations;
	}
	
	private List<CopycatConfiguration> org() {
		CommitResource org = new CommitResource("abc.org", "org", "org", "");
		List<CopycatConfiguration> configurations = org.configurations();
		
		configurations.add(org.configuration("src\\main\\webapp\\files"));
		
		return configurations;
	}
	
	public static void main(String[] args) {
		new FileCommiter().synchronize();	
	}
	
}
