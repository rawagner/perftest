package org.jboss.perf.test.server.enums;

public enum ThresholdStatus {
	NONE(null),
	GLOBAL("G"),
	LOCAL("L");
	
	private String flag;
	
	private ThresholdStatus(String flag) {
		this.flag = flag;
	}
	
	public String getFlag() {
		return flag;
	}
}
