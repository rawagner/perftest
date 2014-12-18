package org.jboss.perf.test.client.listener.junit;

import java.lang.annotation.Annotation;

import org.apache.log4j.Logger;
import org.jboss.perf.test.client.config.BaseConfig;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public class BasePerfListener extends RunListener  {
	
	private static final Logger logger = Logger.getLogger(BasePerfListener.class);
	
	private RunListener listener;
	
	public void initialize(BaseConfig config) {		
		if (config.getRepUrl() == null || config.getProject() == null ||
			config.getBuild() == null || config.getTestSuite() == null ||
			config.getPlatform() == null || config.getSigar().getNativeLibrary() == null) {
			
			listener = new RunListener();
		} else {
			listener = new WorkingListener(config);
		}
	}
	
	public void testRunStarted(Description description)	throws java.lang.Exception {
		logger.info("run started");
		listener.testRunStarted(description);
	}

	public void testRunFinished(Result result) throws java.lang.Exception {
		logger.info("run finished");
		listener.testRunFinished(result);
	}

	public void testStarted(Description description) throws java.lang.Exception {
		logger.info("test started");
		for(Annotation a:description.getAnnotations()){
			logger.info("Annotation: "+ a.annotationType().getCanonicalName());
		}
		listener.testStarted(description);
	}

	public void testFinished(Description description) throws java.lang.Exception {
		logger.info("test finished");
		listener.testFinished(description);
	}

	public void testFailure(Failure failure) throws java.lang.Exception {
		logger.info("test failed");
		listener.testFailure(failure);
	}

	public void testIgnored(Description description) throws java.lang.Exception {
		logger.info("test igored");
		listener.testIgnored(description);
	}
	
	public void testAssumptionFailure(Failure failure) {
		listener.testAssumptionFailure(failure);
	}
}
