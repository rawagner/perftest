package org.jboss.perf.test.server.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jboss.perf.test.server.dao.TestRunDao;
import org.jboss.perf.test.server.model.TestRun;


@ManagedBean
@RequestScoped
public class TestRunBean {
	@EJB
	private TestRunDao testRunDao;
	
	public List<TestRun> getSuccessfullTestRuns(long testSuiteRunId) {
		return testRunDao.getSuccessfullTestRuns(testSuiteRunId);
	}
	
	public int getSuccessfullTestRunsSize(long testSuiteRunId) {
		List<TestRun> testRuns= testRunDao.getSuccessfullTestRuns(testSuiteRunId); 
		return (testRuns != null) ? testRuns.size() : 0;
	}
	
	public List<TestRun> getFailedTestRuns(long testSuiteRunId) {
		return testRunDao.getFailedTestRuns(testSuiteRunId);
	}
	
	public int getFailedTestRunsSize(long testSuiteRunId) {
		List<TestRun> testRuns= testRunDao.getFailedTestRuns(testSuiteRunId); 
		return (testRuns != null) ? testRuns.size() : 0;
	}
}
