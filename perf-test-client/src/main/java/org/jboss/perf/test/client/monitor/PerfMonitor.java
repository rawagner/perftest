package org.jboss.perf.test.client.monitor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.jboss.perf.test.client.attrs.Att;
import org.jboss.perf.test.client.attrs.MAtt;
import org.jboss.perf.test.client.communicator.Method;
import org.jboss.perf.test.client.config.BaseConfig;
import org.jboss.perf.test.objects.constants.AttrUnitStatus;
import org.jboss.perf.test.objects.constants.RelURL;
import org.jboss.perf.test.objects.objects.AttrResultData;
import org.jboss.perf.test.objects.objects.AttrUnit;
import org.jboss.perf.test.objects.objects.Attribute;
import org.jboss.perf.test.objects.objects.ConfigProblem;
import org.jboss.perf.test.objects.objects.Result;

public class PerfMonitor {
	private static final Logger logger = Logger.getLogger(PerfMonitor.class);
	private MonitorCore core;
	private boolean isLocalMonitor = true;
	
	public PerfMonitor() {
		core = MonitorCore.getInstance();
	}
	
	private void printConfigProblems(ConfigProblem configProblem) {
		for(AttrUnit attrUnit: configProblem.getProblems()) {
			if (attrUnit.getStatus() == AttrUnitStatus.ATTR_ERR) {
				logger.warn("Attribute " + attrUnit.getAttr() + " is not defined on the server.");
			} else if (attrUnit.getStatus() == AttrUnitStatus.UNIT_ERR) {
				logger.warn("Unit " + attrUnit.getUnit() + 
				    " used for attribute " + attrUnit.getAttr() + " is not defined on the server.");
			} else if (attrUnit.getStatus() == AttrUnitStatus.UNIT_ERR_ASSIGNED) {
				logger.warn("Unit " + attrUnit.getUnit() + 
				    " is not assigned to attribute " +  attrUnit.getAttr() + " on the server.");	
			} else if (attrUnit.getStatus() == AttrUnitStatus.ATTR_UNIT_ERR) {
				logger.warn("Attribute " + attrUnit.getAttr() + 
				    " and its unit " + attrUnit.getUnit() + " are not defined on the server.");
			}
		}
	}
	
	// Method turns the PerfMonitor into remote mode
	public PerfMonitor asRemote() {
		isLocalMonitor = false;
		return this;
	}
	
	// Method turns the PerfMonitor into remote mode a set its automatic attributes
	public PerfMonitor asRemote(BaseConfig config) {
		isLocalMonitor = false;
		core = core.initialize(config);
		return this;
	}
	
	public void initializeRemotePerfMonitor(final String remotePerfMonitorURL) {
		core.getCommunicator().setUpConnection(remotePerfMonitorURL + "?repurl=" + core.getConfig().getRepUrl() + "&testsuiterunid="+ core.getTestSuiteRunId(), Method.GET);
		logger.debug("INITIALIZE REMOTE PERF_MONITOR");
		// sends GET request on an initialization servlet of remote PerfMonitor
		core.getCommunicator().receiveData();
	}
	
	public void gainAttributes() {
		Map<String, Attribute> attrsMap = core.getAttrResultData().getAttrResult();
		for(Att att: core.getConfig().getAutoAttributes()) {
			att.gainAndSetTime();			
			att.setValue(att.gainValue());
			if (!attrsMap.containsKey(att.getName())) {
				// inicialization
				attrsMap.put(att.getName(), new Attribute(att.getUnit()));
			}
			// insert value
			attrsMap.get(att.getName()).getResults().add(new Result(att.getTime(), att.getValue()));
		}
	}
	
	public void gainAttributes(MAtt[] attrs) {
		// gain auto attributes
		gainAttributes();
		// set manual attributes
		setAttributes(attrs);
	}
	
	public void setAttributes(MAtt[] attrs) {
		Map<String, Attribute> attrsMap = core.getAttrResultData().getAttrResult();
		if (attrs != null) {
			for(MAtt attr: attrs) {
				if (!attrsMap.containsKey(attr.getName())) {
					// inicialization
					attrsMap.put(attr.getName(), new Attribute(attr.getUnit()));
				} 
				attrsMap.get(attr.getName()).getResults().add(new Result(attr.getTime(), attr.getValue()));
			}
		}
	}
	
	public void sendAttributes() {
		if (isLocalMonitor) {
			core.getCommunicator().setUpConnection(core.getConfig().getRepUrl() + RelURL.TEST_RUN + core.getTestRunId(), Method.POST);
		} else {
			String repURL = System.getProperty("repURL");
	    	String testSuiteRunId = System.getProperty("testSuiteRunId");
	    	
	    	if (repURL == null || testSuiteRunId == null) {
	    		// remote PerfMonitor is not correctly initialized
	    		
	    		// clean buffer
	    		core.setAttrResultData(new AttrResultData());
	    		// finish method without sending any data
	    		return;
	    	}
	    	logger.debug("Remote PerfMonitor sending data which belong to TestSuiteRunID: " + testSuiteRunId + 
	    	    " to URL: " + repURL);
			core.getCommunicator().setUpConnection(repURL + RelURL.REMOTE_TEST_SUITE_RUN + testSuiteRunId, Method.POST);
		}
		
		String data = core.getGson().toJson(core.getAttrResultData(), AttrResultData.class);
		logger.debug("SEND PERFORMANCE DATA");
		String response = core.getCommunicator().sendDataAndReceiveAnswer(data);
		if (response != null) {
			ConfigProblem configProblem = core.getGson().fromJson(response, ConfigProblem.class);
			if (configProblem != null) {
				printConfigProblems(configProblem);
			}
		}
		// clean buffer
		core.setAttrResultData(new AttrResultData());
	}
	
	public void sendAttributes(MAtt[] attrs) {
		setAttributes(attrs);
		sendAttributes();
	}
	
	public void gainAttributesAndSend() {
		gainAttributes();
		sendAttributes();
	}
	
	public void gainAttributesAndSend(MAtt[] attrs) {
		gainAttributes();
		setAttributes(attrs);
		// automatic and manual attributes are send
		sendAttributes();
	}	
}
