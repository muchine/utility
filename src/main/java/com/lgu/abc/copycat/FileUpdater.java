package com.lgu.abc.copycat;

import java.util.ArrayList;
import java.util.List;

import com.lgu.abc.copycat.core.CopycatConfiguration;
import com.lgu.abc.copycat.core.FileCopier;
import com.lgu.abc.copycat.resource.CommonResource;
import com.lgu.abc.copycat.resource.UpdateResource;

public class FileUpdater {

	public void synchronize() {
		FileCopier copier = new FileCopier(newConfigurations());
		copier.copy(false);
	}

	private List<CopycatConfiguration> newConfigurations() {
		List<CopycatConfiguration> configurations = new ArrayList<CopycatConfiguration>();
		
		configurations.addAll(address());
		configurations.addAll(new UpdateResource("abc.board", "brd").configurations());
		configurations.addAll(new UpdateResource("abc.calendar", "calendar", "sch", "calendar", "sch").configurations());
		configurations.addAll(new UpdateResource("abc.calendar", "sch").configurations());
		configurations.addAll(new UpdateResource("abc.common", "apr").configurations());
		configurations.addAll(new UpdateResource("abc.common", "approval", "apr", "apr", "apr").configurations());
		configurations.addAll(new UpdateResource("abc.common", "cnfg").configurations());
		configurations.addAll(new UpdateResource("abc.common", "usr").configurations());
		configurations.addAll(core());
		configurations.addAll(new UpdateResource("abc.equipment", "equipment", "equipment", "eqm").configurations());
		configurations.addAll(new UpdateResource("abc.home", "customer").configurations());
		configurations.addAll(new UpdateResource("abc.home", "home").configurations());
		configurations.addAll(new UpdateResource("abc.journal", "jnl").configurations());
		configurations.addAll(new UpdateResource("abc.mail", "mai").configurations());
		configurations.addAll(new UpdateResource("abc.mail", "mail", "mail", "mai").configurations());
		configurations.addAll(new UpdateResource("abc.messaging.note", "messaging\\note", "messaging\\note", "not").configurations());
		configurations.addAll(new UpdateResource("abc.org", "org").configurations());
		configurations.addAll(new UpdateResource("abc.webdisk", "doc").configurations());
		configurations.addAll(workspace());
		
		configurations.add(docs());
		
		return configurations;
	}
	
	private List<CopycatConfiguration> address() {
		UpdateResource address = new UpdateResource("abc.address", "address",
				new String[] {"address"}, new String[] {"address"}, new String[] {"address", "adr"});
		return address.configurations();
	}
	
	private List<CopycatConfiguration> core() {
		UpdateResource core = new UpdateResource("abc.core", "core",
				new String[] {"abc-common", "abc-pns", "abc-topnoti"}, 
				new String[] {"common\\editor"}, 
				new String[] {"abc-common", "abc-pns", "abc.topnoti"});
		
		List<CopycatConfiguration> configurations = core.configurations();
		configurations.add(core.configuration("src\\main\\webapp\\resources\\abc\\images"));
		configurations.add(core.configuration("src\\main\\webapp\\resources\\abc\\css").recursive(false));
		
		return configurations;
	}
	
	private List<CopycatConfiguration> workspace() {
		UpdateResource workspace = new UpdateResource("abc.workspace", "workspace", 
				new String[] {"workspace"}, new String[] {"workspace"}, new String[] {"ws\\info"});
		
		return workspace.configurations();
	}
	
	private CopycatConfiguration docs() {
		return new CopycatConfiguration(CommonResource.OLD_ROOT + "\\docs", CommonResource.NEW_ROOT + "\\docs");
	}
	
	public static void main(String[] args) {
		new FileUpdater().synchronize();	
	}
	
}
