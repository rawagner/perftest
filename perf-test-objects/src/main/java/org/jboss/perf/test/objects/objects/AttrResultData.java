package org.jboss.perf.test.objects.objects;

import java.util.HashMap;
import java.util.Map;


public class AttrResultData {
	private Map<String, Attribute> attrResult;
	
	public AttrResultData() {
		attrResult = new HashMap<String, Attribute>();
	}

	public void setAttrResult(HashMap<String, Attribute> attrResult) {
		this.attrResult = attrResult;
	}

	public Map<String, Attribute> getAttrResult() {
		return attrResult;
	}
}
