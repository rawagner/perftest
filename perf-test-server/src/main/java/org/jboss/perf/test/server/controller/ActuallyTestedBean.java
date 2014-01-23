package org.jboss.perf.test.server.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.jboss.perf.test.server.dao.TestSuiteRunDao;
import org.jboss.perf.test.server.model.TestSuiteRun;


@ManagedBean
@ViewScoped
public class ActuallyTestedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private TestSuiteRunDao testSuiteRunDao;
	private List<TestSuiteRun> testSuiteRuns;
	
	@PostConstruct
	public void init() {
		testSuiteRuns = testSuiteRunDao.getActuallyTestedTestSuiteRunsByStartTimeDesc();
	}

	public List<TestSuiteRun> getTestSuiteRuns() {
		return testSuiteRuns;
	}
}
