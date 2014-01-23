package org.jboss.perf.test.server.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.jboss.perf.test.server.dao.ProjectDao;
import org.jboss.perf.test.server.dao.TestSuiteRunDao;
import org.jboss.perf.test.server.model.Project;
import org.jboss.perf.test.server.model.TestSuiteRun;


@ManagedBean
@ViewScoped
public class TestSuiteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private TestSuiteRunDao testSuiteRunDao;
	@EJB
	private ProjectDao projectDao;
	
	private long projectId;
	private Project project;
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		projectId = Long.parseLong(fc.getExternalContext().getRequestParameterMap().get("projectid"));
		project = projectDao.getProjectById(projectId); 
	}
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	public Project getProject() {
		return project;
	}
	
	public List<TestSuiteRun> getTestSuiteRuns() {
		return testSuiteRunDao.getTestSuiteRunsByProjectIdByStartTimeDesc(projectId);
	}
	
	public void deleteTestSuiteRun(TestSuiteRun testSuiteRun) {
		testSuiteRunDao.remove(testSuiteRun);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "TestSuite " + testSuiteRun.getTestSuite().getName() + " with all its data was deleted.", null);
		FacesContext.getCurrentInstance().addMessage("form:delete", msg);
	}
}
