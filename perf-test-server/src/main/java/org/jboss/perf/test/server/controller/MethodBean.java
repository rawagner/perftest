package org.jboss.perf.test.server.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.jboss.perf.test.server.dao.TestRunDao;
import org.jboss.perf.test.server.dao.TestSuiteRunDao;
import org.jboss.perf.test.server.model.TestRun;
import org.jboss.perf.test.server.model.TestSuiteRun;




@ManagedBean
@ViewScoped
public class MethodBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private TestRunDao testRunDao;
	@EJB
	private TestSuiteRunDao testSuiteRunDao;
	
	private long testSuiteRunId;
	private TestSuiteRun testSuiteRun;
	private List<TestRun> testRuns; 
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		testSuiteRunId = Long.parseLong(fc.getExternalContext().getRequestParameterMap().get("testsuiterunid"));
		testSuiteRun = testSuiteRunDao.getTestSuiteRunById(testSuiteRunId);
		testRuns = testRunDao.getAllTestRuns(testSuiteRunId);
	}
	
	public TestSuiteRun getTestSuiteRun() {
		return testSuiteRun;
	}
	
	public List<TestRun> getTestRuns() {
		return testRuns;
	}
}
